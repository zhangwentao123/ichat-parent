//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.thebo.ichat.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    public static final String CHARSET = "UTF-8";
    private static ThreadLocal<Map<String, String>> httpHeader = new ThreadLocal();
    private static ThreadLocal<Map<String, Object>> httpClientConfig = new ThreadLocal();
    public static final String CONNECT_TIMEOUT = "connect_timeout";
    public static final String SOCKET_TIMEOUT = "socket_timeout";
    public static final Integer DEFAULT_CONNECT_TIMEOUT = Integer.valueOf(600000);
    public static final Integer DEFAULT_SOCKET_TIMEOUT = Integer.valueOf(600000);

    public HttpClientUtils() {
    }

    public static CloseableHttpClient buildHttpClient() {
        Object configSetting = new HashMap();
        if(httpClientConfig != null && null != httpClientConfig.get()) {
            configSetting = (Map)httpClientConfig.get();
        }

        Builder builder = RequestConfig.custom();
        Integer connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        if(((Map)configSetting).get("connect_timeout") != null) {
            try {
                connectTimeout = Integer.valueOf(((Map)configSetting).get("connect_timeout").toString());
            } catch (Exception var6) {
                logger.warn("class covert error!", var6);
                connectTimeout = DEFAULT_CONNECT_TIMEOUT;
            }
        }

        builder.setConnectTimeout(connectTimeout.intValue());
        Integer socketTimeout = DEFAULT_SOCKET_TIMEOUT;
        if(((Map)configSetting).get("socket_timeout") != null) {
            try {
                socketTimeout = Integer.valueOf(((Map)configSetting).get("socket_timeout").toString());
            } catch (Exception var5) {
                logger.warn("class covert error!", var5);
                socketTimeout = DEFAULT_SOCKET_TIMEOUT;
            }
        }

        builder.setSocketTimeout(socketTimeout.intValue());
        RequestConfig config = builder.build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, "UTF-8");
    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, "UTF-8");
    }

    public static String doGet(String url, Map<String, String> params, String charset) throws IOException {
        if(StringUtils.isBlank(url)) {
            return null;
        } else {
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpGet httpGet = null;

            String value;
            try {
                if(params != null && !params.isEmpty()) {
                    ArrayList e = new ArrayList(params.size());
                    Iterator entity = params.entrySet().iterator();

                    while(entity.hasNext()) {
                        Entry result = (Entry)entity.next();
                        value = (String)result.getValue();
                        if(value != null) {
                            e.add(new BasicNameValuePair((String)result.getKey(), value));
                        }
                    }

                    url = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(e, charset));
                }

                httpGet = new HttpGet(url);
                handlerHeader(httpGet);
                httpClient = buildHttpClient();
                response = httpClient.execute(httpGet);
                int e1 = response.getStatusLine().getStatusCode();
                if(e1 != 200) {
                    httpGet.abort();
                    throw new RuntimeException("HttpClient,error status code :" + e1);
                }

                HttpEntity entity1 = response.getEntity();
                String result1 = null;
                if(entity1 != null) {
                    result1 = EntityUtils.toString(entity1, "utf-8");
                }

                EntityUtils.consume(entity1);
                response.close();
                value = result1;
            } catch (IOException var13) {
                logger.error(var13.getMessage(), var13);
                throw var13;
            } finally {
                if(response != null) {
                    response.close();
                }

                if(httpGet != null) {
                    httpGet.releaseConnection();
                }

                if(httpClient != null) {
                    httpClient.close();
                }

            }

            return value;
        }
    }

    private static void handlerHeader(HttpRequestBase requestBase) {
        if(httpHeader != null && httpHeader.get() != null) {
            Map map = (Map)httpHeader.get();
            Iterator i$ = map.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                requestBase.addHeader(key, (String)map.get(key));
            }
        }

    }

    public static String doPost(String url, Map<String, String> params, String charset) throws IOException {
        if(StringUtils.isBlank(url)) {
            return null;
        } else {
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpPost httpPost = null;

            String var10;
            try {
                ArrayList e = null;
                String result;
                if(params != null && !params.isEmpty()) {
                    e = new ArrayList(params.size());
                    Iterator statusCode = params.entrySet().iterator();

                    while(statusCode.hasNext()) {
                        Entry entity = (Entry)statusCode.next();
                        result = (String)entity.getValue();
                        if(result != null) {
                            e.add(new BasicNameValuePair((String)entity.getKey(), result));
                        }
                    }
                }

                httpPost = new HttpPost(url);
                handlerHeader(httpPost);
                if(e != null && e.size() > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(e, "UTF-8"));
                }

                httpClient = buildHttpClient();
                response = httpClient.execute(httpPost);
                int statusCode1 = response.getStatusLine().getStatusCode();
                if(statusCode1 != 200) {
                    httpPost.abort();
                    throw new RuntimeException("HttpClient,error status code :" + statusCode1);
                }

                HttpEntity entity1 = response.getEntity();
                result = null;
                if(entity1 != null) {
                    result = EntityUtils.toString(entity1, "utf-8");
                }

                EntityUtils.consume(entity1);
                var10 = result;
            } catch (IOException var14) {
                logger.error(var14.getMessage(), var14);
                throw var14;
            } finally {
                if(response != null) {
                    response.close();
                }

                if(httpPost != null) {
                    httpPost.releaseConnection();
                }

                if(httpClient != null) {
                    httpClient.close();
                }

            }

            return var10;
        }
    }

    public static String doPost(String url, String jsonParam) throws IOException {
        if(StringUtils.isBlank(url)) {
            return null;
        } else {
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpPost httpPost = null;

            String var8;
            try {
                httpPost = new HttpPost(url);
                handlerHeader(httpPost);
                if(StringUtils.isNotBlank(jsonParam)) {
                    StringEntity e = new StringEntity(jsonParam);
                    e.setContentEncoding("UTF-8");
                    e.setContentType("application/json");
                    httpPost.setEntity(e);
                }

                httpClient = buildHttpClient();
                response = httpClient.execute(httpPost);
                int e1 = response.getStatusLine().getStatusCode();
                if(e1 != 200) {
                    httpPost.abort();
                    throw new RuntimeException("HttpClient,error status code :" + e1);
                }

                HttpEntity entity = response.getEntity();
                String result = null;
                if(entity != null) {
                    result = EntityUtils.toString(entity, "utf-8");
                }

                EntityUtils.consume(entity);
                var8 = result;
            } catch (IOException var12) {
                logger.error(var12.getMessage(), var12);
                throw var12;
            } finally {
                if(response != null) {
                    response.close();
                }

                if(httpPost != null) {
                    httpPost.releaseConnection();
                }

                if(httpClient != null) {
                    httpClient.close();
                }

            }

            return var8;
        }
    }

    public static void setHeader(Map<String, String> header) {
        if(header != null) {
            httpHeader.set(header);
        }

    }

    public static void setConfig(Map<String, Object> config) {
        if(config != null) {
            httpClientConfig.set(config);
        }

    }
}
