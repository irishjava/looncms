package uk.mafu.loon.util;

import java.util.Date;
import org.joda.time.DateTime;

public class DateUtil {
	public static Date[] getStartAndEndOfYear(int year) {
		Date[] group = new Date[2];
		DateTime dateTime = new DateTime(year, 1, 1, 0, 0, 0, 0);
		group[0] = dateTime.toDate();
		group[1] = dateTime.plusYears(1).toDate();
		return group;
	}
}
