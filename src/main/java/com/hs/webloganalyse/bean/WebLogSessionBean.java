package com.hs.webloganalyse.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebLogSessionBean {
	String time;
	String IP_addr;
	String session;
	String request_URL;
	String referal;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIP_addr() {
		return IP_addr;
	}

	public void setIP_addr(String iP_addr) {
		IP_addr = iP_addr;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getRequest_URL() {
		return request_URL;
	}

	public void setRequest_URL(String request_URL) {
		this.request_URL = request_URL;
	}

	public String getReferal() {
		return referal;
	}

	public void setReferal(String referal) {
		this.referal = referal;
	}

	public Date getTimeWithDateFormat() {

		SimpleDateFormat sdf_final = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.time != null && this.time != "") {
			try {
				return sdf_final.parse(this.time);
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return time + " " + IP_addr + " " + session + " " + request_URL + " " + referal;
	}
}
