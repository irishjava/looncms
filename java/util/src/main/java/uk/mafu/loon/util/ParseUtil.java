package uk.mafu.loon.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;

public class ParseUtil {
	@SuppressWarnings("unchecked")
	public static Map<Integer, List<String>> yearMultiRowParse(String input) {
		try {
			Comparator<Integer> c = new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return -1;
					} else if (o1 < o2) {
						return 1;
					} else {
						return 0;
					}
				}
			};
			SortedMap<Integer, List<String>> map = new TreeMap<Integer, List<String>>(
					c);
			List<String> readLines = (List<String>) IOUtils
					.readLines(new StringReader(input));
			String currentkey;
			List<String> lines = null;
			for (String string : readLines) {
				if (string.matches("[0-9]{4}")) {
					// new key
					currentkey = string;
					lines = new ArrayList<String>();
					map.put(new Integer(currentkey), lines);
				} else {
					lines.add(string);
				}
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException();
		}
	}
}