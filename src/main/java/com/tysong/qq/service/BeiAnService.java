package com.tysong.qq.service;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  BeiAnService {
    INSTANCE;
	private static String API = "https://api.vvhan.com/api/icp?url=";
	
    @SuppressWarnings("unchecked")
	public String exec(String url, String qqNum) {
    	
    	String response = "";
    	String result = "";
    	try{
    		response = HttpClientUtil.httpGetRequest(API + url);
        	Map<String, String> result_map = JSONObject.parseObject(response, HashMap.class);
        	String info = JSONObject.toJSONString(result_map.get("info"));
        	if("null".equals(info) || StringUtil.isEmpty(info)) {
        		result += result_map.get("message");
        	}
        	else if(StringUtil.isNotEmpty(result_map) && StringUtil.isNotEmpty(info)) {
        		Map<String, String> temp_map = JSONObject.parseObject(info, HashMap.class);
        		result += "网站名称：" + StringUtil.stringValueOf(temp_map.get("title"), "0") + "\n";
        		result += "备案性质：" + StringUtil.stringValueOf(temp_map.get("nature"), "0") + "\n";
        		result += "备案号：" + StringUtil.stringValueOf(temp_map.get("icp"), "0") + "\n";
        		result += "备案主体：" + "********" + "\n";
        	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		result += "接口挂了";
    	}
    	
    	CatCodeUtil util = CatCodeUtil.INSTANCE;
        CodeTemplate<String> template = util.getStringTemplate();
        result = template.at(qqNum) + "\n" + result;
        return result;
    }
}
