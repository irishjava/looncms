package uk.mafu.loon.util;

import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class ParseUtilTest {
	@Test
	public void testYearMultiRowParse() {
		String input = "2009\n" + "one\n" + "two\n" + "three\n" + "2008\n"
				+ "four\n" + "five\n" + "2007\n";
		Map<Integer, List<String>> parse = ParseUtil.yearMultiRowParse(input);
		assertTrue("2009 first", (2009 == (Integer)parse.keySet().toArray()[0]));
		assertTrue("Three keys", parse.keySet().size() == 3);
		assertTrue("2009,3 items", parse.get(2009).size() == 3);
		assertTrue("2008,2 items", parse.get(2008).size() == 2);
		assertTrue("2007,0 items", parse.get(2007).size() == 0);
	}
} 