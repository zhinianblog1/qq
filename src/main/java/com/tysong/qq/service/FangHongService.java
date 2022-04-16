package com.tysong.qq.service;


import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  FangHongService {
    INSTANCE;
	private static String API = "http://mc9.ltd/includes/duansc/ZLJZ.php?type=mcurls&longurl=";
	
	public String exec(String url, String qqNum) {	
    	
    	String response = "";
    	String result = "";
    	try{
    		response = HttpClientUtil.httpGetRequest(API + url);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		result += "接口挂了";
    	}
    	
    	CatCodeUtil util = CatCodeUtil.INSTANCE;
        CodeTemplate<String> template = util.getStringTemplate();
        if(StringUtil.isNotEmpty(response)) {
        	result = template.at(qqNum) + "\n" + "防洪链接如下：" + response;
        }
        return result;
    }
}
