package uk.mafu.loon.exception;

import uk.mafu.loon.LoonExceptionTranslator;

public class LoonExceptionTranslatorImpl implements LoonExceptionTranslator {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger
//			.getLogger(LoonExceptionTranslatorImpl.class);

	/**
	 * After much reflection, I decided we never want to tell the remote client
	 * what went wrong, better to diagnose via the server logs.
	 */
	public RuntimeException translate(Throwable t) {
		return new UnsupportedOperationException("Internal server Error");
	}
}