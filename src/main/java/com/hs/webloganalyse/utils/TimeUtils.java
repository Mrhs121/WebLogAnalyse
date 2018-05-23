package com.hs.webloganalyse.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public static String getCurrentDate() {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(curDate);
		// return "2018-04-19";
		return dateStr;
	}

	/**
	 * 获取当前的日期
	 * 
	 * @param p
	 *            指定格式
	 * @return
	 */
	public static String getCurrentDate(String p) {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		String dateStr = sdf.format(curDate);
		return dateStr;
	}

	public static void getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
		System.out.println(yesterday);
	}

	public static void main(String[] args) {

	}

}
