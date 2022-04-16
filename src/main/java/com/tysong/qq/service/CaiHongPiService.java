package com.tysong.qq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public enum CaiHongPiService {
    INSTANCE;
    private static final String DEFAULT="你是最棒的";
    private static final String host="http://api.tianapi.com/txapi/caihongpi/index?key=e69af0102b9173941576821dfcce812d";
    public String caihongpi(String input){
        String response = HttpClientUtil.httpGetRequest(host);
        if (StringUtils.isNotBlank(response)){
            JSONObject rootObject=JSONObject.parseObject(response);
            if (rootObject==null){
                return DEFAULT;
            }
            JSONArray newslist = rootObject.getJSONArray("newslist");
            if (CollectionUtils.isEmpty(newslist)){
                return DEFAULT;
            }
            JSONObject wordObject = newslist.getJSONObject(0);
            String caihongpi = wordObject.getString("content");
            if(StringUtils.isNotBlank(caihongpi)){
                return caihongpi;
            }else{
                return DEFAULT;
            }
        }
        return DEFAULT;
    }
}
