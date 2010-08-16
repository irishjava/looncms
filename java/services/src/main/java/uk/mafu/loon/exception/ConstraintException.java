package uk.mafu.loon.exception;

import java.sql.BatchUpdateException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityExistsException;
import org.apache.log4j.Logger;

public class ConstraintException extends RuntimeException {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ConstraintException.class);
	private static final long serialVersionUID = 1L;
	private String parent;
	private String child;
	private static Pattern FOREIGN_KEY_CONSTRAINT = Pattern
			.compile(
					".*: a foreign key constraint fails\\s\\(`.*?/(.*?)`.*REFERENCES\\s`(.*?)`.*",
					Pattern.UNICODE_CASE);

	EntityExistsException root;

	public ConstraintException(EntityExistsException e) {
		this.root = e;
		if (e.getCause() != null && e.getCause().getCause() != null) {
			Throwable cause2 = e.getCause().getCause();
			if (cause2.getClass() == BatchUpdateException.class) {
				BatchUpdateException constraintViolationException = (BatchUpdateException) cause2;
				extractDetails((constraintViolationException));
			}
		}
	}

	private void extractDetails(BatchUpdateException ex) {
		String msg = ex.getMessage();
		Matcher m = FOREIGN_KEY_CONSTRAINT.matcher(msg);
		if (m.matches()) {
			this.child = m.group(2);
			this.parent = m.group(1);
		} else {
			logger.error("matcher did not match, ex='" + root.getStackTrace()
					+ "' msg was:" + msg);
		}
	}

	@Override
	public String getMessage() {
		return "Cannot delete a record of type: '" + child
				+ "'. This record is referenced by another of type: '" + parent
				+ "'.";
	}

	@Override
	public String toString() {
		return getMessage();
	}
}