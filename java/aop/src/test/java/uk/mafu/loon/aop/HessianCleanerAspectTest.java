package uk.mafu.loon.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.internal.AroundClosure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.mafu.loon.LoonExceptionTranslator;

public class HessianCleanerAspectTest {

	private static HessianCleanerAspect cleaner;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		cleaner = new HessianCleanerAspect(new LoonExceptionTranslator() {
			public RuntimeException translate(Throwable t) {
				System.err.println(t);
				return new RuntimeException(t);
			}
		});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHessianCleanerAspect() {
		final List<String> vals = new ArrayList<String>();
		vals.add("test");
		cleaner.decorate(createProceedingJoinPoing(vals));
	}

	private ProceedingJoinPoint createProceedingJoinPoing(final Object ret) {
		return new ProceedingJoinPoint() {

			public Object proceed() throws Throwable {
				return ret;
			}

			public Object proceed(Object[] arg0) throws Throwable {
				return null;
			}

			public void set$AroundClosure(AroundClosure arg0) {

			}

			public Object[] getArgs() {
				return new String[] { "dummy", "args" };
			}

			public String getKind() {

				return "kind";
			}

			public Signature getSignature() {

				return null;
			}

			public SourceLocation getSourceLocation() {

				return null;
			}

			public StaticPart getStaticPart() {
				return null;
			}

			public Object getTarget() {
				return null;
			}

			public Object getThis() {
				return null;
			}

			public String toLongString() {
				return null;
			}

			public String toShortString() {
				return null;
			}
		};
	}

	@Test
	public void testDecorate() {

	}
}