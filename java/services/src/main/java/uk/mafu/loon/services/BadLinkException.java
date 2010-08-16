package uk.mafu.loon.services;

public class BadLinkException extends UnsupportedOperationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8245541412034758758L;

	public BadLinkException() {
		super();
	}

	public BadLinkException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadLinkException(String message) {
		super(message);
	}

	public BadLinkException(Throwable cause) {
		super(cause);
	}
}
