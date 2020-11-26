package com.psq.train.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * HttpUtil
 * Description: http请求工具类
 *
 * @author dyh
 * Copyright © 2018-2019  创捷运维智能科技有限公司
 * All rights reserved.
 * @version: 1.0
 * Reversion:
 * 1.0 - 新建
 */
public class HttpUtil {
    static final Logger _LOG = LoggerFactory.getLogger(HttpUtil.class);

    public static final String CHARSET = "UTF-8";
    private static final int TIMEOUT = 6000;

    private static CloseableHttpClient httpClient;

    /**
     * 初始化
     */
    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(200);
        cm.setValidateAfterInactivity(1000 * 120);

        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * Description: 封装的post方法，发送json字符串
     *
     * @param url
     * @param params
     * @param header
     * @return java.lang.String
     * @Author: Peng Shiquan
     * @Date: 2020/11/10
     */
    public static String doPost(String url, String params, Map<String, String> header) {
        // 判断请求参数
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return doPost(url, params, header, CHARSET);
    }

    /**
     * Description: 发送json字符串的post请求
     *
     * @param url     请求的URL
     * @param params  json字符串
     * @param header  请求头
     * @param charset 字符编码
     * @return java.lang.String
     * @Author: Peng Shiquan
     * @Date: 2020/11/10
     */
    private static String doPost(String url, String params, Map<String, String> header, String charset) {

        // 声明对象
        CloseableHttpResponse response = null;
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置header
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (null == value && value.length() == 0) continue;
                    httpPost.setHeader(key, value);
                }
            }
            if (null != params && !"".equals(params)) {
                httpPost.setEntity(new StringEntity(params, charset));
            }
            // 调用http请求,返回响应值
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                return null;
            }
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
                _LOG.info("调用doPost方法返回值：" + result);
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            _LOG.error("请求" + url + "失败：" + e.getMessage(), e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    _LOG.error("CloseableHttpResponse关闭时报错: ", e);
                }
            }
        }
        return result;
    }
}
