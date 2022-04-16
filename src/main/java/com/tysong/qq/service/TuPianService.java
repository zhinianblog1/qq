package com.tysong.qq.service;


import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  TuPianService {
    INSTANCE;
	
	private static String API = "http://jiuli.xiaoapi.cn/i/qun_fgg.php?uin=2938633328&skey=@W4mbJr7jA&pskey=4HsVGy2Hr2dG04YnPA*kQIntJDq4yF5rXZ*1TVPQkjA_&qh=198963776&title=测试&text=测试";
	
	public String exec() {
    	
    	String result = "";
    	try{
    		result = HttpClientUtil.httpGetRequest(API);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(StringUtil.isNotEmpty(result)) {
    		CatCodeUtil util = CatCodeUtil.INSTANCE;
    		CodeTemplate<String> template = util.getStringTemplate();
    	}
        return result;
    }
}
