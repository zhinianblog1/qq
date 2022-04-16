package com.tysong.qq.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * @author 
 * @version 1.0 2016年10月21日
 * @see HtmlUtil
 * @since 1.0
 */
public final class HtmlUtil {
	
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(HtmlUtil.class);

	
	private static HtmlUtil _HtmlUtil;
	
	/**
	 * 解析url
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> analyseUrl(String url) {
		Map<String, String> result = new HashMap<>();
		String title = "";
		try {
			String html = getOneHtml(url);
			title = outTag(getTitle(html));
			result.put("title", title);
			result.put("des", title);
		} catch (Exception e) {
			logger.error("获取文章标题失败：", e);
		}
		return result;
	}

	/**
	 * 读取一个网页全部内容
	 * @throws Exception 
	 */
	public static String getOneHtml(final String htmlurl) {
		URL url;
		String temp;
		final StringBuffer sb = new StringBuffer();
		try {
			HtmlUtil.Instance().TrustHpps();
			url = new URL(htmlurl);
			// 读取网页全部内容
			final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			logger.error("你输入的URL格式有问题！请仔细输入：", me);
		} catch (final IOException e) {
			logger.error("解析url失败：", e);
		} catch (Exception e) {
			logger.error("解析url失败：", e);
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param s
	 * @return 获得网页标题
	 */
	public static String getTitle(final String s) {
		String regex;
		String title = "";
		final List<String> list = new ArrayList<String>();
		regex = "<title>.*?</title>";
		final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}
		return outTag(title);
	}
	

	/**
	 * 
	 * @param s
	 * @return 去掉标记
	 */
	public static String outTag(final String s) {
		return s.replaceAll("<.*?>", "");
	}
	
	/**
     * Http请求封装实例
     * */
    public static HtmlUtil Instance()
    {
        if (_HtmlUtil == null)
        {
        	_HtmlUtil = new HtmlUtil();
        }
        return _HtmlUtil;
    }
	
	/**
     * 信任HTTPS
     * */
    public void TrustHpps() throws Exception
    {
        HttpsURLConnection
                .setDefaultHostnameVerifier(new NullHostNameVerifier());
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, HtmlUtil.TrustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
    
    /**
     * 信任证书管理
     * */
    private static TrustManager[] TrustAllCerts = new TrustManager[]
    {
        new X509TrustManager()
        {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

        }
    };

    /**
     * 主机证书认证
     * */
    private class NullHostNameVerifier implements HostnameVerifier
    {
        /*
         * (non-Javadoc)
         * @see javax.net.ssl.HostnameVerifier#verify(java.lang.String,
         * javax.net.ssl.SSLSession)
         */
        @Override
        public boolean verify(String arg0, SSLSession arg1)
        {
            // TODO Auto-generated method stub
            return true;
        }
    }
}