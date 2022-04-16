package com.tysong.qq.service;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  JinQunService {
    INSTANCE;
	private static String API = "https://api.dzzui.com/api/yiyan?format=json";
	
	@SuppressWarnings("unchecked")
	public String exec() {
    	
    	String result = "";
    	try{
    		result = HttpClientUtil.httpGetRequest(API);
    		Map<String, String> result_map = JSONObject.parseObject(result, HashMap.class);
    		if(StringUtil.isNotEmpty(result_map) && StringUtil.isNotEmpty(result_map.get("text"))) {
    			result = result_map.get("text");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return result;
    }
}
