package com.nova.nsar.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String toString(Date obj, String format) {
		if (obj == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(obj);
	}
}
