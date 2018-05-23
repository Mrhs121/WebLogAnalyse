package com.hs.webloganalyse.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hs.webloganalyse.bean.WebLogBean;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 解析web日志 eg: 124.42.13.230 - - [18/Sep/2013:06:57:50 +0000] "GET
 * /shoppingMall?ver=1.2.1 HTTP/1.1" 200 7200 "http://www.baidu.com.cn"
 * "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; BTRS101170;
 * InfoPath.2; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727)"
 * 
 * @author 黄晟
 *
 */
public class WebLogParser {

	private static UserAgent userAgent = null;
	private static Browser browser = null;

	/**
	 * 解析日志，封装成对象，并且过滤掉脏数据
	 * 
	 * @param origindata
	 *            原始数据
	 * @return
	 */
	public static WebLogBean parser(String origindata) {
		WebLogBean weblogbean = new WebLogBean();

		// 获取IP地址
		Pattern IPPattern = Pattern.compile("\\d+.\\d+.\\d+.\\d+");
		Matcher IPMatcher = IPPattern.matcher(origindata);
		if (IPMatcher.find()) {
			String IPAddr = IPMatcher.group(0);
			weblogbean.setIp(IPAddr);
		} else {
			return null;
		}
		
		
		// 获取时间信息
		Pattern TimePattern = Pattern.compile("\\[(.+)\\]");
		Matcher TimeMatcher = TimePattern.matcher(origindata);
		if (TimeMatcher.find()) {
			String time = TimeMatcher.group(1);
			String[] cleanTime = time.split(" ");
			weblogbean.setTime(cleanTime[0]);
		} else {
			return null;
		}

		
		
		// 获取其余请求信息
		Pattern InfoPattern = Pattern.compile("(\\\"[POST|GET].+?\\\") (\\d+) (\\d+).+?(\\\".+?\\\") (\\\".+?\\\")");

		Matcher InfoMatcher = InfoPattern.matcher(origindata);
		if (InfoMatcher.find()) {
			System.out.println("0 :" + InfoMatcher.group(0));
			System.out.println("1 :" + InfoMatcher.group(1));
			String requestInfo = InfoMatcher.group(1).replace('\"', ' ').trim();
			String[] requestInfoArry = requestInfo.split(" ");
			weblogbean.setMethod(requestInfoArry[0]);
			weblogbean.setRequestURL(requestInfoArry[1]);
			weblogbean.setRequestProtocol(requestInfoArry[2]);
			String status_code = InfoMatcher.group(2);
			weblogbean.setRespondCode(status_code);

			String respond_data = InfoMatcher.group(3);
			weblogbean.setRespondDataLength(respond_data);

			String request_come_from = InfoMatcher.group(4).replace('\"', ' ').trim();
			weblogbean.setRequst_come_from(request_come_from);
			System.out.println(InfoMatcher.group(5));
			String browserInfo = InfoMatcher.group(5).replace('\"', ' ').trim();
			weblogbean.setBrowser(browserInfo);
		} else {
			return null;
		}

		return weblogbean;
	}

	/**
	 * 解析浏览器数据，系统版本
	 * 
	 * @param origindata
	 *            原始数据
	 * @return
	 */
	public static String parserBrowser(String origindata) {
		String[] strings = origindata.split(" ");
		StringBuilder ua = new StringBuilder();

		for (int j = 8; j < strings.length; j++) {
			ua.append(strings[j] + " ");
		}
		
		try {
			userAgent = UserAgent.parseUserAgentString(ua.toString());
			browser = userAgent.getBrowser();
		} catch (Exception e) {
			return "";
		}

		return browser.getName().replaceAll(" ","");
	}

}
