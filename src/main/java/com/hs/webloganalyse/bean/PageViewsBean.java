package com.hs.webloganalyse.bean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class PageViewsBean {
        
		// sessiomid
        String session;
        // ip
        String IP_addr;
        // 访问时间
        String time;
        // 访问的url
        String visit_URL;
        // 停留时间
        String stayTime;
        // 第几步
        String step;
        
        public String getSession() {
                return session;
        }
        public void setSession(String session) {
                this.session = session;
        }
        public String getIP_addr() {
                return IP_addr;
        }
        public void setIP_addr(String iP_addr) {
                IP_addr = iP_addr;
        }
        public String getTime() {
                return time;
        }
        public void setTime(String time) {
                this.time = time;
        }
        public String getVisit_URL() {
                return visit_URL;
        }
        public void setVisit_URL(String visit_URL) {
                this.visit_URL = visit_URL;
        }
        public String getStayTime() {
                return stayTime;
        }
        public void setStayTime(String stayTime) {
                this.stayTime = stayTime;
        }
        public String getStep() {
                return step;
        }
        public void setStep(String step) {
                this.step = step;
        }
         
        public Date getTimeWithDateFormat() {
                 
                SimpleDateFormat sdf_final = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(this.time != null && this.time != "") {
                        try {
                                return sdf_final.parse(this.time);
                        } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
                return null;
        }
         
        @Override
        public String toString() {
                return session + " " + IP_addr + " " + time + " "
                                + visit_URL + " " + stayTime + " " + step;
        }
                 
}
