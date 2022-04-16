package com.tysong.qq.service;

import com.alibaba.fastjson.JSONObject;
import com.tysong.qq.bean.weather.HeWeather5;
import com.tysong.qq.bean.weather.Result;
import com.tysong.qq.bean.weather.WeatherBean;
import com.tysong.qq.utils.HttpClientUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public enum TianQiService {
    INSTANCE;
    private static final String host = "https://way.jd.com/he/freeweather?appkey=1d33eb24db0c44f04cdda85133fddf70&city=";
    private static final String DEFAULT = "查询不到天气哦";

    public String getWeather(String city) {
        String response = HttpClientUtil.httpGetRequest(host + city);
        if (StringUtils.isNotBlank(response)) {
            WeatherBean weatherBean = JSONObject.parseObject(response, WeatherBean.class);
            if (weatherBean == null) {
                return DEFAULT;
            }
            Result result = weatherBean.getResult();
            if (result == null) {
                return DEFAULT;
            }
            List<HeWeather5> heWeather5 = result.getHeWeather5();
            if (CollectionUtils.isNotEmpty(heWeather5)) {
                HeWeather5 heWeather51 = heWeather5.get(0);
                if (heWeather51 == null) {
                    return DEFAULT;
                }
                String tmp = "";
                String txt = "";
                if (heWeather51.getNow() != null) {
                    tmp = heWeather51.getNow().getTmp();
                    if (heWeather51.getNow().getCond() != null) {
                        txt = heWeather51.getNow().getCond().getTxt();
                    }
                }
                String qlty = "";
                if (heWeather51.getAqi() != null && heWeather51.getAqi().getCity() != null) {
                    qlty = heWeather51.getAqi().getCity().getQlty();
                }
                String drsgTxt = "";
                if (heWeather51.getSuggestion() != null && heWeather51.getSuggestion().getDrsg() != null) {
                    drsgTxt = heWeather51.getSuggestion().getDrsg().getTxt();
                }
                String outText = city + ":\n" + "    空气质量: " + qlty + "\n    温度:" + tmp + "度\n    天气:" + txt + "\n    穿衣建议:" + drsgTxt;
                return outText;
            }
        }
        return DEFAULT;
    }
}
