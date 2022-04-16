package com.tysong.qq.service;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import catcode.CatCodeUtil;

public enum  WebSiteInfoService {
    INSTANCE;
	private static String API = "https://api.vvhan.com/api/icp?url=";
	
    @SuppressWarnings("unchecked")
	public String exec(String url, String qqNum) {
    	return "[CAT:xml,content=<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"2\" templateID=\"12345\" action=\"web\" brief=\"有未领取的红包\" sourceMsgId=\"0\" url=\"https://qin0.cn/\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"2\"><audio cover=\"https://wy-1308483560.cos.ap-shanghai.myqcloud.com/2021/12/22bfcfe2aacfa158-1.png\" src=\"https://qin0.cn/wp-content/themes/zibll/qin/wy.mp3\" /><title>七年的避风港</title><summary>还在外面干嘛呢？进来坐啊</summary></item><source name=\"\" icon=\"\" action=\"\" appid=\"-1\" /></msg>]";
//    	Document doc = null;
//        try {
//            doc = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate")
//            	    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
//            	    .maxBodySize(0)
//            	    .timeout(600000)
//            	    .get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String title = doc.title();
//        Elements metas = doc.head().select("meta");
//        String keywords = "";
//        String description = "";
//        for (Element meta : metas) { 
//            String content = meta.attr("content"); 
//            if ("keywords".equalsIgnoreCase(meta.attr("name"))) { 
//                keywords = content;
//            } 
//            if ("description".equalsIgnoreCase(meta.attr("name"))) { 
//                description = content;
//            } 
//        } 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	CatCodeUtil util = CatCodeUtil.INSTANCE;
//    	return "[CAT:share,url=https://zhinianblog.com,title=标题,content=描述,coverUrl=https://img.zhinianblog.com/img/1328]";
//    	return "[CAT:share,title=标题,content=简述,url=https://zhinianblog.com,coverUrl=https://img.zhinianblog.com/img/1328]";
//        return "[CAT:xml,content=<?xml version=1.0 encoding=UTF-8 ?>,actionData=zhinianblog.com]";
//    	StringBuffer sb = new StringBuffer();
//    	sb.append("[CAT:xml,content=<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>");
//    	sb.append("<msg serviceID=\"2\" templateID=\"12345\" action=\"web\" brief=\"网站详情\" sourceMsgId=\"0\" ");
//    	sb.append("url=" + "\"" + url + "\" ");
//    	sb.append("flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"2\">");
////    	sb.append("<audio cover=\"https://wy-1308483560.cos.ap-shanghai.myqcloud.com/2021/12/22bfcfe2aacfa158-1.png\" src=\"https://qin0.cn/wp-content/themes/zibll/qin/wy.mp3\" />");
//    	sb.append("<title>" + title + "</title>");
//    	sb.append("<summary>网站关键字：" + keywords + "" + "网站描述：" + description + "</summary>");
//    	sb.append("</item><source name=\"\" icon=\"\" action=\"\" appid=\"-1\" /></msg>]");
//    	System.out.println(sb.toString());
//    	return "[CAT:xml,content=<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"2\" templateID=\"12345\" "
//    			+ "action=\"web\" brief=\"有未领取的红包\" sourceMsgId=\"0\" "
//    			+ "url=\"https://qin0.cn/\" flag=\"0\" adverSign=\"0\" "
//    			+ "multiMsgFlag=\"0\"><item layout=\"2\">"
//    			+ "<audio cover=\"https://wy-1308483560.cos.ap-shanghai.myqcloud.com/2021/12/22bfcfe2aacfa158-1.png\" "
//    			+ "src=\"https://qin0.cn/wp-content/themes/zibll/qin/wy.mp3\" />"
//    			+ "<title>"+title+"</title>"
//    					+ "<summary>网站关键字："+ keywords + "网站描述："+description+"</summary>"
//    					+ "</item><source name=\"\" icon=\"\" action=\"\" appid=\"-1\" /></msg>]";
    }
    
    public static void main(String[] args) {
    	Document doc = null;
        try {
            doc = Jsoup.connect("https://zhinianblog.com").header("Accept-Encoding", "gzip, deflate")
            	    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
            	    .maxBodySize(0)
            	    .timeout(600000)
            	    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        Elements metas = doc.head().select("meta"); 
        for (Element meta : metas) { 
            String content = meta.attr("content"); 
            if ("keywords".equalsIgnoreCase(meta.attr("name"))) { 
                System.out.println("关键字："+content); 
            } 
            if ("description".equalsIgnoreCase(meta.attr("name"))) { 
                System.out.println("网站内容描述:"+content); 
            } 
        } 
        Elements keywords = doc.getElementsByTag("meta");
        System.out.println("标题"+title);
	}
}
