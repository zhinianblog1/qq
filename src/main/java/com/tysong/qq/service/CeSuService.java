package com.tysong.qq.service;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  CeSuService {
    INSTANCE;
	private static String API = "https://www.rqvq.cn/api.php?id=12&url=";
	
	public String exec(String url, String qqNum) {
    	
    	String result = "";
    	try{
    		result = HttpClientUtil.httpGetRequest(API + url);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		result += "接口挂了";
    	}
    	if(!StringUtil.isNotEmpty(result)) {
    		result += "接口挂了";
    	}
    	CatCodeUtil util = CatCodeUtil.INSTANCE;
        CodeTemplate<String> template = util.getStringTemplate();
        result = template.at(qqNum) + "\n" + result;
        return result;
    }
}
