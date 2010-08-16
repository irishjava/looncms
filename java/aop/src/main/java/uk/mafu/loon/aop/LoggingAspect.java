package uk.mafu.loon.aop;

import org.apache.log4j.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoggingAspect.class);

	public Object log(ProceedingJoinPoint call) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("invoke logger '" + call.toShortString() + "',parameters:'" + getParams(call) +"'");
		}
		Object point = call.proceed();
		if (logger.isDebugEnabled()) {
			logger.debug("invoke logger '" + call.toShortString() + "',returns:'" + point + "'");
		}
		return point;
	}

	private String getParams(ProceedingJoinPoint call) {
		String ret = "";
		for (Object obj : call.getArgs()) {
			ret += ":" + obj + ":";
		}
		return ret;
	}
}