package com.caucho.hessian.client;

import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.caucho.hessian.io.HessianRemoteObject;

/**
 * Implements a cookie preserving Proxy Factor for when you are running a
 * stateful service.
 * 
 * @author Mattias Jiderhamn
 * 
 */
public class CookieHessianProxyFactory extends HessianProxyFactory {

	public Object create(Class api, String urlName, ClassLoader loader)
			throws MalformedURLException {
		URL url = new URL(urlName);

		CookieHessianProxy handler = new CookieHessianProxy(this, url);

		return Proxy.newProxyInstance(api.getClassLoader(), new Class[] { api,
				HessianRemoteObject.class }, handler);
	}

	private static class CookieHessianProxy extends HessianProxy {

		/** List of cookies */
		private List<String> cookieStrings = null;

		CookieHessianProxy(HessianProxyFactory factory, URL url) {
			super(url, factory);
		}

		protected void parseResponseHeaders(URLConnection conn) {
			List<String> _cookieStrings = conn.getHeaderFields().get(
					"Set-Cookie");
			if (_cookieStrings != null)
				cookieStrings = _cookieStrings;
		}

		protected void addRequestHeaders(URLConnection conn) {
			if (cookieStrings != null) {
				for (String cookieString : cookieStrings) {
					conn.setRequestProperty("Cookie", cookieString);
				}
			}
		}
	}
}
