package com.thebo.ichat.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 短链接工具类
 * @author yinyafei
 * @date 2015.09.21
 */
public class ShortUrlUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ShortUrlUtils.class);

	private static final String BAIDU_DWZ_GENERATE = "http://qqurl.com/create/";
	private static final String BAIDU_DWZ_RECOVERY = "http://dwz.cn/query.php";

	public static String generateShortUrl(String longUrl) {

		HttpClient httpclient = HttpClientUtils.buildHttpClient();
		HttpPost httpost = new HttpPost(BAIDU_DWZ_GENERATE);

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("url", longUrl));
		String shortUrlString = "";
		try {
			httpost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			logger.debug("生成短链接返回:{}", jsonStr);
			JSONObject object = JSON.parseObject(jsonStr);
			shortUrlString = object.getString("short_url");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shortUrlString;

	}

	public static String recoverylongUrl(String shortUrl) {

		HttpClient httpclient = HttpClientUtils.buildHttpClient();
		HttpPost httpost = new HttpPost(BAIDU_DWZ_RECOVERY);

		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("tinyurl", shortUrl));
		String longUrlString = "";
		try {
			httpost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			longUrlString = object.getString("longurl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return longUrlString;
	}
	
	public static void main(String[] args) {
		String shortUrl = generateShortUrl("http://test.51ika.com/");
		System.out.println(shortUrl);

		String longUrlString = recoverylongUrl("http://dwz.cn/1KwflN");
		System.out.println(longUrlString);
	}
}
