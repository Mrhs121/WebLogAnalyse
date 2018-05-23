package com.hs.webloganalyse.utils;

import java.util.ArrayList;

import com.hs.webloganalyse.bean.VisitsInfoBean;

public class VisitsInfoParser {
	 public String parser(ArrayList<String> pageViewsGroup,String totalVisitNum) {
         
         VisitsInfoBean visitsBean = new VisitsInfoBean();
         String entryPage = pageViewsGroup.get(0).split(" ")[4];
         String leavePage = pageViewsGroup.get(pageViewsGroup.size()-1).split(" ")[4];
         String startTime = pageViewsGroup.get(0).split(" ")[0] + " " + pageViewsGroup.get(0).split(" ")[1];
         String endTime = pageViewsGroup.get(pageViewsGroup.size()-1).split(" ")[0] +
                                 " " +pageViewsGroup.get(pageViewsGroup.size()-1).split(" ")[1];
         String session = pageViewsGroup.get(0).split(" ")[3];
         String IP = pageViewsGroup.get(0).split(" ")[2];
         String referal = pageViewsGroup.get(0).split(" ")[5];
          
         visitsBean.setSession(session);
         visitsBean.setStart_time(startTime);
         visitsBean.setEnd_time(endTime);
         visitsBean.setEntry_page(entryPage);
         visitsBean.setLeave_page(leavePage);
         visitsBean.setVisit_page_num(totalVisitNum);
         visitsBean.setIP_addr(IP);
         visitsBean.setReferal(referal);
          
         return visitsBean.toString();
 }
}