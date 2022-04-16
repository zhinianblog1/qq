package com.tysong.qq.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.utils.HttpClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public enum  ChengyuService {
    INSTANCE;
    private static final String DEFAULT="接龙失败,在下认输";
    private static final String host="http://api.tianapi.com/txapi/chengyujielong/index?key=ebf13e1b4bbaec9478a2389d9191ab27&userid=98105401289c18d858c169578ecf0125&word=";
    public String jieLong(String input){
        String response = HttpClientUtil.httpGetRequest(host + input);
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
            String chengyu = wordObject.getString("chengyu");
            if(StringUtils.isNotBlank(chengyu)){
                return chengyu;
            }else{
                return DEFAULT;
            }
        }
        return DEFAULT;
    }
}
