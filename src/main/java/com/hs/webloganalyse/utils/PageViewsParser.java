package com.hs.webloganalyse.utils;

import com.hs.webloganalyse.bean.PageViewsBean;

public class PageViewsParser {
	 /**
     * 根据logSession的输出数据加载PageViewsBean
     *
     * */
    public PageViewsBean loadBean(String sessionContent) {
             
            PageViewsBean pageViewsBean = new PageViewsBean();
                     
            String[] contents = sessionContent.split(" ");
            pageViewsBean.setTime(contents[0] + " " + contents[1]);
            pageViewsBean.setIP_addr(contents[2]);
            pageViewsBean.setSession(contents[3]);
            pageViewsBean.setVisit_URL(contents[4]);
            pageViewsBean.setStayTime("0");
            pageViewsBean.setStep("0");
                                     
            return pageViewsBean;
    }
     
    public String parser(PageViewsBean pageViewBean) {
             
            return pageViewBean.toString();
    }
}
