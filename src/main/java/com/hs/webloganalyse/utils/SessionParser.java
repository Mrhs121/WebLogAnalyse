package com.hs.webloganalyse.utils;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.hs.webloganalyse.bean.WebLogSessionBean;

 
public class SessionParser {
         
        SimpleDateFormat sdf_origin = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",Locale.ENGLISH);
        SimpleDateFormat sdf_final = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
        public String parser(WebLogSessionBean sessionBean,String sessionID) {
                                 
                sessionBean.setSession(sessionID);                
                return sessionBean.toString();
        }
         
        public WebLogSessionBean loadBean(String sessionContent) {
                 
                WebLogSessionBean weblogSession = new WebLogSessionBean();
                         
                String[] contents = sessionContent.split(" ");
                weblogSession.setTime(timeTransform(contents[1]));
                weblogSession.setIP_addr(contents[0]);
                weblogSession.setRequest_URL(contents[3]);
                weblogSession.setReferal(contents[7]);
                         
                return weblogSession;
        }
         
        private String timeTransform(String time) {
                                 
                Date standard_time = null;
                try {
                        standard_time = sdf_origin.parse(time);
                } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return sdf_final.format(standard_time);
        }
}
