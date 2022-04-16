package com.tysong.blog.service;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.dto.WebResult;
import com.tysong.qq.listener.JinYanListener;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  BlogDiaoYongService {
    INSTANCE;
	private static String ADD_API = "https://dy.zhinianblog.com/robot/addWeiJinWord";
	
	private static String SEARCH_API = "https://dy.zhinianblog.com/robot/searchWeiJinWord";
	
	public String addWeiJinWord(String keyword, String qqNum) {
    	String response = "";
    	String result = "";
    	WebResult webResult = new WebResult();
    	try{
    		Map<String, String> param = new HashMap<>();
    		param.put("weijinWord", keyword);
    		response = HttpClientUtil.getResult(ADD_API, JSONObject.toJSONString(param));
    		webResult = JSONObject.parseObject(response, WebResult.class);
    		result = webResult.getMessage();
    		JinYanListener.WEIJIN_WORD = webResult.getMsg();
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
	
	public String searchWeiJinWord() {
    	String response = "";
    	WebResult webResult = new WebResult();
    	try{
    		Map<String, String> param = new HashMap<>();
    		response = HttpClientUtil.getResult(SEARCH_API, JSONObject.toJSONString(param));
    		webResult = JSONObject.parseObject(response, WebResult.class);
    		JinYanListener.WEIJIN_WORD = webResult.getMsg();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return webResult.getMsg();
    }
}
