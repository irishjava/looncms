package uk.mafu.loon.custom.hessian;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.caucho.HessianClientInterceptor;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.SimpleHessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.util.NestedServletException;

import com.caucho.hessian.io.AbstractHessianInput;
import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;
import com.caucho.hessian.server.HessianSkeleton;

/**
 * Servlet-API-based HTTP request handler that exports the specified service
 * bean as Hessian service endpoint, accessible via a Hessian proxy.
 * 
 * <p>
 * <b>Note:</b> Spring also provides an alternative version of this exporter,
 * for Sun's JRE 1.6 HTTP server: {@link SimpleHessianServiceExporter}.
 * 
 * <p>
 * Hessian is a slim, binary RPC protocol. For information on Hessian, see the
 * <a href="http://www.caucho.com/hessian">Hessian website</a>.
 * 
 * <p>
 * This exporter will work with both Hessian 2.x and 3.x (respectively Resin 2.x
 * and 3.x), autodetecting the corresponding skeleton class. As of Spring 2.0,
 * it is also compatible with the new Hessian 2 protocol (a.k.a. Hessian
 * 3.0.20+), while remaining compatible with older versions.
 * 
 * <p>
 * Note: Hessian services exported with this class can be accessed by any
 * Hessian client, as there isn't any special handling involved.
 * 
 * @author bryan hunt ( modified so that it uses hessian 1 protocol
 * @author Juergen Hoeller
 * @since 13.05.2003
 * @see HessianClientInterceptor
 * @see HessianProxyFactoryBean
 * @see org.springframework.remoting.caucho.BurlapServiceExporter
 * @see org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter
 * @see org.springframework.remoting.rmi.RmiServiceExporter
 */
public class Hessian1ServiceExporter extends RemoteExporter implements
		InitializingBean, HttpRequestHandler {

	static class Hessian1SkeletonInvoker {

		private static final Method invokeMethod;

		private static final boolean applySerializerFactoryToOutput;

		static {
			invokeMethod = ClassUtils.getMethodIfAvailable(
					HessianSkeleton.class, "invoke", new Class[] {
						AbstractHessianInput.class, AbstractHessianOutput.class });
			applySerializerFactoryToOutput = ClassUtils.hasMethod(
					HessianOutput.class, "setSerializerFactory",
					new Class[] { SerializerFactory.class });
		}

		/**
		 * Wrapped HessianSkeleton, available to subclasses.
		 */
		protected final HessianSkeleton skeleton;

		/**
		 * Hessian SerializerFactory (if any), available to subclasses.
		 */
		protected final SerializerFactory serializerFactory;

		public Hessian1SkeletonInvoker(HessianSkeleton skeleton,
				SerializerFactory serializerFactory) {
			this.skeleton = skeleton;
			this.serializerFactory = serializerFactory;
			Assert.notNull(skeleton, "HessianSkeleton must not be null");
			if (invokeMethod == null) {
				throw new IllegalStateException(
						"Hessian 1 (version 3.0.19-) not present");
			}
		}

		public void invoke(InputStream inputStream, OutputStream outputStream)
				throws Throwable {
			HessianInput in = new HessianInput(inputStream);
			HessianOutput out = new HessianOutput(outputStream);
			if (this.serializerFactory != null) {
				in.setSerializerFactory(this.serializerFactory);
				if (applySerializerFactoryToOutput) {
					out.setSerializerFactory(this.serializerFactory);
				}
			}
			try {
				invokeMethod.invoke(this.skeleton, new Object[] { in, out });
			} finally {
				try {
					in.close();
					inputStream.close();
				} catch (IOException ex) {
				}
				try {
					out.close();
					outputStream.close();
				} catch (IOException ex) {
				}
			}

		}

	}

	/**
	 * Internal invoker strategy for a Hessian skeleton. Allows for common
	 * handling of Hessian protocol version 1 and 2.
	 * 
	 * @author Juergen Hoeller
	 * @since 2.0
	 */
	private SerializerFactory serializerFactory = new SerializerFactory();

	@SuppressWarnings("unused")
	private Log debugLogger;

	private Hessian1SkeletonInvoker skeletonInvoker;

	/**
	 * Specify the Hessian SerializerFactory to use.
	 * <p>
	 * This will typically be passed in as an inner bean definition of type
	 * <code>com.caucho.hessian.io.SerializerFactory</code>, with custom bean
	 * property values applied.
	 */
	public void setSerializerFactory(SerializerFactory serializerFactory) {
		this.serializerFactory = (serializerFactory != null ? serializerFactory
				: new SerializerFactory());
	}

	/**
	 * Set whether to send the Java collection type for each serialized
	 * collection. Default is "true".
	 */
	public void setSendCollectionType(boolean sendCollectionType) {
		this.serializerFactory.setSendCollectionType(sendCollectionType);
	}

	/**
	 * Set whether Hessian's debug mode should be enabled, logging to this
	 * exporter's Commons Logging log. Default is "false".
	 * 
	 * @see com.caucho.hessian.client.HessianProxyFactory#setDebug
	 */
	public void setDebug(boolean debug) {
		this.debugLogger = (debug ? logger : null);
	}

	public void afterPropertiesSet() {
		prepare();
	}

	/**
	 * Initialize this exporter.
	 */
	@SuppressWarnings("unchecked")
	public void prepare() {
		HessianSkeleton skeleton = null;

		try {
			try {
				// Try Hessian 3.x (with service interface argument).
				Constructor ctor = HessianSkeleton.class
						.getConstructor(new Class[] { Object.class, Class.class });
				checkService();
				checkServiceInterface();
				skeleton = (HessianSkeleton) ctor.newInstance(new Object[] {
						getProxyForService(), getServiceInterface() });
			} catch (NoSuchMethodException ex) {
				// Fall back to Hessian 2.x (without service interface
				// argument).
				Constructor ctor = HessianSkeleton.class
						.getConstructor(new Class[] { Object.class });
				skeleton = (HessianSkeleton) ctor
						.newInstance(new Object[] { getProxyForService() });
			}
		} catch (Throwable ex) {
			throw new BeanInitializationException(
					"Hessian skeleton initialization failed", ex);
		}

		// Hessian 1 (version 3.0.19-).
		this.skeletonInvoker = new Hessian1SkeletonInvoker(skeleton,
				this.serializerFactory);
	}

	/**
	 * Perform an invocation on the exported object.
	 * 
	 * @param inputStream
	 *            the request stream
	 * @param outputStream
	 *            the response stream
	 * @throws Throwable
	 *             if invocation failed
	 */
	public void invoke(InputStream inputStream, OutputStream outputStream)
			throws Throwable {
		Assert.notNull(this.skeletonInvoker,
				"Hessian exporter has not been initialized");
		ClassLoader originalClassLoader = overrideThreadContextClassLoader();
		try {
			this.skeletonInvoker.invoke(inputStream, outputStream);
		} finally {
			resetThreadContextClassLoader(originalClassLoader);
		}
	}

	/**
	 * Processes the incoming Hessian request and creates a Hessian response.
	 */
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!"POST".equals(request.getMethod())) {
			throw new HttpRequestMethodNotSupportedException(request
					.getMethod(), new String[] { "POST" },
					"HessianServiceExporter only supports POST requests");
		}

		try {
			invoke(request.getInputStream(), response.getOutputStream());
		} catch (Throwable ex) {
			throw new NestedServletException(
					"Hessian skeleton invocation failed", ex);
		}
	}

}
