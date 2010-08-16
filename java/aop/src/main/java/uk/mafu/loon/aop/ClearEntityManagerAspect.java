package uk.mafu.loon.aop;

import org.aspectj.lang.JoinPoint;

public class ClearEntityManagerAspect {
	public void clearEntityManager(final JoinPoint call) throws Throwable {
		int i = 0;
		i++;
	}
}
