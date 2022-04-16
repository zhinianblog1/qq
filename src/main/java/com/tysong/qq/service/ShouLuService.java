package com.tysong.qq.service;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  ShouLuService {
    INSTANCE;
	private static String BAIDU_API = "https://api.uomg.com/api/entry.baidu?domain=";
	private static String SOUGOU_API = "https://api.dzzui.com/api/sogonum?domain=";
	private static String SHENMA_API = "https://api.uomg.com/api/entry.sm?domain=";
	private static String GUGE_API = "https://api.gmit.vip/Api/CheckSEO?format=json&domain=";
	
    @SuppressWarnings("unchecked")
	public String getShouLu(String url, String qqNum) {
    	
    	String response = "";
    	String result = "";
//    	try{
//    		response = HttpClientUtil.httpGetRequest(BAIDU_API + url);
//        	Map<String, String> baidu_shoulu_map = JSONObject.parseObject(response, HashMap.class);
//        	if(StringUtil.isNotEmpty(baidu_shoulu_map)) {
//        		result += "百度收录：" + StringUtil.stringValueOf(baidu_shoulu_map.get("data"), "0");
//        	}
//    	}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    		result += "百度收录：0";
//    	}
//    	
//    	try{
//    		response = HttpClientUtil.httpGetRequest(SOUGOU_API + url);
//        	Map<String, String> sougou_shoulu_map = JSONObject.parseObject(response, HashMap.class);
//        	if(StringUtil.isNotEmpty(sougou_shoulu_map)) {
//        		result += "\n搜狗收录：" + StringUtil.stringValueOf(sougou_shoulu_map.get("num"), "0");
//        	}
//    	}
//    	catch(Exception e) {
//    		e.printStackTrace();
//    		result += "\n搜狗收录：0";
//    	}
    	
    	
    	try{
    		response = HttpClientUtil.httpGetRequest(GUGE_API + url);
        	Map<String, String> guge_shoulu_map = JSONObject.parseObject(response, HashMap.class);
        	String info = "";
        	if(StringUtil.isNotEmpty(guge_shoulu_map)) {
        		info = JSONObject.toJSONString(guge_shoulu_map.get("data"));
        	}
        	if(StringUtil.isNotEmpty(guge_shoulu_map) && StringUtil.isNotEmpty(info) && !"null".equals(info)) {
        		Map<String, Object> result_shoulu_map = JSONObject.parseObject(info, HashMap.class);
        		String title = "";
        		if(StringUtil.isNotEmpty(result_shoulu_map.get("title"))) {
        			title = result_shoulu_map.get("title").toString();
        		}
        		result += "网站标题：" +  title;
        		result += "\n百度收录：" + result_shoulu_map.get("baidu").toString();
        		result += "\n好搜收录：" + result_shoulu_map.get("haoso").toString();
        		result += "\n神马收录：" +  result_shoulu_map.get("sm").toString();;
        		result += "\n搜狗收录：" + result_shoulu_map.get("sogou").toString();
        		result += "\nbing收录：" + result_shoulu_map.get("bingZh").toString();
        		result += "\nbing国际收录：" + result_shoulu_map.get("bing").toString();
        		result += "\n谷歌收录：" + result_shoulu_map.get("google").toString();
        	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		result = "接口挂了";
    	}
    	if(StringUtil.isNotEmpty(result)) {
    		CatCodeUtil util = CatCodeUtil.INSTANCE;
    		CodeTemplate<String> template = util.getStringTemplate();
    		result = template.at(qqNum) + "\n" + result;
    	}
        return result;
    }
}
