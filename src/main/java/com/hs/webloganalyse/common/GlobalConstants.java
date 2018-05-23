package com.hs.webloganalyse.common;

/**
 * 全局字段
 * 
 * @author 黄晟
 *
 */
public class GlobalConstants {
	// 正确数据的长度
	public static final int RIGHT_LINE_LENGTH = 26;
	// 原始数据的路径
	public static final String ORIGIN_WEBLOG_PATH_PREFIX = "/flume/events/";
	// 第一次清洗之后数据的输出路径
	public static final String CLEANED_WEBLOG_PATH_PREFIX  = "/webloganalyse/cleaneddata/";
	// 独立ip结果输出路劲
	public static final String STANDIP_PATH_PREFIX  = "/webloganalyse/standipdata/";
	// 根据访问记录生成相应的session信息的输出路径
	public static final String SESSIONDATA_PATH_PREFIX  = "/webloganalyse/sessiondata/";
	// visits任务的输出路径
	public static final String VISITSINFO_PATH_PREFIX = "/webloganalyse/visitsinfodata/";
	
	public static final String PAGEVIEW_PATH_PREFIX = "/webloganalyse/pageview/";
	
	public static final String BROWSER_PATH__PREFIX = "/webloganalyse/browser/";
	
	public static final String FIRST_CLEAN_TASK = "first_clean_task";
	
	public static final String SESSION_TASK = "create_session_task";
	
	public static final String PAGEVIEW_TASK = "pageview_task";
	
	public static final String VISITS_TASK = "visits_task";
	
	public static final String STANDIP_TASK = "standip_task";
	
	public static final String BROWSER_TASK = "browser_task";
	
	public static final String RETURN_STRING = "string";
	
	public static final String RETURN_OBJECT = "object";

}
