package com.tysong.qq.service;


import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  PingService {
    INSTANCE;
	private static String API = "https://qqlykm.cn/api/ping/ping.php?host=";
	
	public String exec(String url, String qqNum) {
    	
    	String result = "";
    	try{
    		result = HttpClientUtil.httpGetRequest(API + url);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(StringUtil.isNotEmpty(result)) {
    		CatCodeUtil util = CatCodeUtil.INSTANCE;
    		CodeTemplate<String> template = util.getStringTemplate();
    		result = template.at(qqNum) + "\n" + result;
    	}
        return result;
    }
}
