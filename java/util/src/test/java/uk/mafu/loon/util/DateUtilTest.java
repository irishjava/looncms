package uk.mafu.loon.util;

import java.util.Date;
import junit.framework.TestCase;

public class DateUtilTest extends TestCase {
	public DateUtilTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetStartAndEndOfYear() {
		Date[] res = DateUtil.getStartAndEndOfYear(2008);
		System.err.println(res[0]);
		System.err.println(res[1]);
	}
}
