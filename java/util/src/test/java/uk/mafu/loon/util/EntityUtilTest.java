package uk.mafu.loon.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntityUtilTest {

	public class Dummy {
		public Integer pk = 1;
		public String title = "test project";
		public String permalink;
	}

	@Test
	public void testGeneratePermalinkText() {
		Dummy o = new Dummy();
		EntityUtil.generatePermalinkText(o);
		assertEquals("1-test-project", o.permalink);
	}
}