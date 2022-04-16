package com.tysong.qq.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.dto.BlogRobotSerchInfo;
import com.tysong.qq.dto.WebResult;
import com.tysong.qq.utils.HttpClientUtil;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;

public enum  SiteMapService {
    INSTANCE;
	private static String ADD_API = "https://dy.zhinianblog.com/robotSerch/addSiteMap";
	

	private static String UPDATE_API = "https://dy.zhinianblog.com/robotSerch/updateSiteMap";
	
	private static String SEARCH_API = "https://dy.zhinianblog.com/robotSerch/searchArchive";
	
	public String addSiteMap(String url, String qqNum) {
    	String response = "";
    	String result = "";
    	if(!url.contains("http")) {
    		result = "sitemap必须包含http(s)://";
    		CatCodeUtil util = CatCodeUtil.INSTANCE;
    		CodeTemplate<String> template = util.getStringTemplate();
    		result = template.at(qqNum) + "\n" + result;
    		return result;
		}
    	WebResult webResult = new WebResult();
    	try{
    		Map<String, String> param = new HashMap<>();
    		param.put("sitemap", url);
    		param.put("qq", qqNum);
    		response = HttpClientUtil.getResult(ADD_API, JSONObject.toJSONString(param));
    		webResult = JSONObject.parseObject(response, WebResult.class);
    		result = webResult.getMessage();
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
	
	public String updateSiteMap(String url, String qqNum) {
    	String response = "";
    	String result = "";
    	if(!url.contains("http")) {
    		result = "sitemap必须包含http(s)://";
    		CatCodeUtil util = CatCodeUtil.INSTANCE;
    		CodeTemplate<String> template = util.getStringTemplate();
    		result = template.at(qqNum) + "\n" + result;
    		return result;
		}
    	WebResult webResult = new WebResult();
    	try{
    		Map<String, String> param = new HashMap<>();
    		param.put("sitemap", url);
    		param.put("qq", qqNum);
    		response = HttpClientUtil.getResult(UPDATE_API, JSONObject.toJSONString(param));
    		webResult = JSONObject.parseObject(response, WebResult.class);
    		result = webResult.getMessage();
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
	
	public String searchArchive(String searchKey, String qqNum) {
    	String response = "";
    	String result = "";
    	WebResult webResult = new WebResult();
    	try{
    		Map<String, String> param = new HashMap<>();
    		param.put("searchKey", searchKey);
    		response = HttpClientUtil.getResult(SEARCH_API, JSONObject.toJSONString(param));
    		webResult = JSONObject.parseObject(response, WebResult.class);
    		result = webResult.getMessage();
    		StringBuffer sb = new StringBuffer();
    		if(StringUtil.isNotEmpty(result) && result.length() > 5) {
    			List<BlogRobotSerchInfo> resultList = JSONObject.parseArray(result, BlogRobotSerchInfo.class);
    			if(StringUtil.isNotEmptyList(resultList)) {
    				for (BlogRobotSerchInfo blogRobotSerchInfo : resultList) {
    					sb.append(blogRobotSerchInfo.getTitle() + ":" + blogRobotSerchInfo.getUrl() + "\n");
					}
    				sb.append("执念官方合作伙伴☞:https://qin0.cn☜" + "\n");
    				sb.append("~~~~~~~~热门推荐" + ":" + "https://www.xxhzm.cn" + "\n");
    				sb.append("微梦云盘" + ":" + "https://pan.vinua.cn" + "\n");
    				sb.append("云you视频免费解析接口" + ":" + "https://chaloli.cn" + "\n");
    				sb.append("内涵爱好者｜欢迎入驻" + ":" + "http://wcsb.cc" + "\n");
    				result = sb.toString();
    			}
    		}
    		else {
    			result = "未搜索到内容，请确认是否已添加sitemap!";
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
