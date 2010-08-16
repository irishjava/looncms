package uk.mafu.loon.util;

import org.apache.commons.lang.StringUtils;

public class StringMatcher {

	public static boolean Matches(String test, String[] opts) {
		for (String string : opts) {
			if (string.equals(test)) {
				return true;
			}
		}

		return false;
	}

	public static void AssertMatches(String test, String[] opts) {
		if (!Matches(test, opts)) {
			throw new UnsupportedOperationException("test value '" + test
					+ "' not found in opts [" + StringUtils.join(opts, ',')
					+ "]");
		}

	}

}
