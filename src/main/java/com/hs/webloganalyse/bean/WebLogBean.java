package com.hs.webloganalyse.bean;

public class WebLogBean {
	// 客户端的ip
	private String ip;
	// 访问的时间
	private String time;
	// 请求的方法
	private String method;
	// 请求的URL
	private String requestURL;
	// 请求的协议
	private String requestProtocol;
	// 响应状态码
	private String respondCode;
	// 响应的数据
	private String respondDataLength;
	// 来自哪个链接
	private String requst_come_from;
	// 通过什么浏览器发起的访问
	private String browser;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public String getRequestProtocol() {
		return requestProtocol;
	}

	public void setRequestProtocol(String requestProtocol) {
		this.requestProtocol = requestProtocol;
	}

	public String getRespondCode() {
		return respondCode;
	}

	public void setRespondCode(String respondCode) {
		this.respondCode = respondCode;
	}

	public String getRespondDataLength() {
		return respondDataLength;
	}

	public void setRespondDataLength(String respondDataLength) {
		this.respondDataLength = respondDataLength;
	}

	public String getRequst_come_from() {
		return requst_come_from;
	}

	public void setRequst_come_from(String requst_come_from) {
		this.requst_come_from = requst_come_from;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public String toString() {
		return ip + " " + time + " " + method + " " + requestURL + " " + requestProtocol + " " + respondCode + " "
				+ respondDataLength + " " + requst_come_from + " " + browser;
	}

}
