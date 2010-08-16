package uk.mafu.loon.aop;

import javax.persistence.Entity;
import org.aspectj.lang.ProceedingJoinPoint;
import uk.mafu.loon.util.EntityUtil;

public class PermalinkAspect {
	
	
//	/**
//	 * Logger for this class
//	 */
//	private static final Logger logger = Logger.getLogger(PermalinkAspect.class);
//
//	private String appendArgs(final Object[] args) {
//		final StringBuffer ret = new StringBuffer();
//		for (final Object object : args) {
//			ret.append(":");
//			ret.append(object);
//			ret.append(":");
//		}
//		return ret.toString();
//	}

	public Object decorate(final ProceedingJoinPoint call) {
		try {
			// if (logger.isDebugEnabled()) {
			// logger.debug("clean(ProceedingJoinPoint) - from logging aspect:
			// entering method ["
			// + call.toShortString() + "] with params:" +
			// appendArgs(call.getArgs()));
			// }
			// Get the target as we are processing incoming requests..
			Object[] args = call.getArgs();
			// Object point = call.getArgs();
			for (Object point : args) {
				if (point.getClass().getAnnotation(Entity.class) != null) {
					EntityUtil.generatePermalinkText(point);
				}
			}
			return call.proceed();
			// if (logger.isDebugEnabled()) {
			// logger.debug("clean(ProceedingJoinPoint) - from logging aspect:
			// exiting method ["
			// + call.toShortString() + "with return as:" + point);
			// }
			// return point;
		} catch (final Throwable t) {
			throw new UnsupportedOperationException(t);
		}
	}
}