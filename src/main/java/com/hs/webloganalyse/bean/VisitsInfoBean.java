package com.hs.webloganalyse.bean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class VisitsInfoBean {
         
        String session;
        String start_time;
        String end_time;
        String entry_page;
        String leave_page;
        String visit_page_num;
        String IP_addr;
        String referal;
         
        public String getSession() {
                return session;
        }
        public void setSession(String session) {
                this.session = session;
        }
        public String getStart_time() {
                return start_time;
        }
        public void setStart_time(String start_time) {
                this.start_time = start_time;
        }
        public String getEnd_time() {
                return end_time;
        }
        public void setEnd_time(String end_time) {
                this.end_time = end_time;
        }
        public String getEntry_page() {
                return entry_page;
        }
        public void setEntry_page(String entry_page) {
                this.entry_page = entry_page;
        }
        public String getLeave_page() {
                return leave_page;
        }
        public void setLeave_page(String leave_page) {
                this.leave_page = leave_page;
        }
        public String getVisit_page_num() {
                return visit_page_num;
        }
        public void setVisit_page_num(String visit_page_num) {
                this.visit_page_num = visit_page_num;
        }
        public String getIP_addr() {
                return IP_addr;
        }
        public void setIP_addr(String iP_addr) {
                IP_addr = iP_addr;
        }
        public String getReferal() {
                return referal;
        }
        public void setReferal(String referal) {
                this.referal = referal;
        }
         
        @Override
        public String toString() {
                return session + " " + start_time + " " + end_time
                                + " " + entry_page + " " + leave_page + " " + visit_page_num
                                + " " + IP_addr + " " + referal;
        }
         
         
                 
}