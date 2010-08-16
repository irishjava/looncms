package uk.mafu.loon.exception;

import java.sql.BatchUpdateException;
import javax.persistence.EntityExistsException;
import junit.framework.JUnit4TestAdapter;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;

public class ConstraintExceptionTest {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(ConstraintExceptionTest.class));
	}

	@Test
	public final void testConstraintException() {
		// Cannot delete or update a parent row: a foreign key constraint fails
		// (`gillespies/Assignment`, CONSTRAINT `FKB3FD62ED3759041A` FOREIGN KEY
		// (`person_pk`) REFERENCES `Person` (`pk`))
		// public ConstraintException(EntityExistsException e) {
		// if (e.getCause() != null && e.getCause().getCause() != null) {
		// Throwable cause2 = e.getCause().getCause();
		// if (cause2.getClass() == BatchUpdateException.class) {
		// BatchUpdateException constraintViolationException =
		// (BatchUpdateException) cause2;
		// extractDetails((constraintViolationException));
		// }
		// }
		// }
		BatchUpdateException batchUpdateException = new BatchUpdateException(
				"Cannot delete or update a parent row: a foreign key constraint fails (`gillespies/Assignment`, CONSTRAINT `FKB3FD62ED3759041A` FOREIGN KEY (`person_pk`) REFERENCES `Person` (`pk`))",
				new int[] {});
		ConstraintViolationException constraintException = new ConstraintViolationException("", batchUpdateException,
				"");
		EntityExistsException entityExistsException = new EntityExistsException(constraintException);
		// my class
		ConstraintException c = new ConstraintException(entityExistsException);
		String message = c.getMessage();
		Assert.assertTrue("Cannot delete a record of type: 'Person'. This record is referenced by another of type: 'Assignment'."
				.equals(message));
	}
}
