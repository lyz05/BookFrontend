/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.utils;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.bean.User;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.lang.reflect.Field;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;

/**
 *
 * @author congcong
 */
public class BookFrontedCon {

    private BookFrontedCon() {
    }       //禁止实例化

    private static final String baseurl = "https://tomcat.home999.cc/book";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(2000)
            .setSocketTimeout(10000).build();

    public static String getBaseurl() {
        return baseurl;
    }

    /**
     * GET请求 参数携带在url中
     *
     * @param url
     * @return
     */
    public static String executeGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = null;

        try {
            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(entity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String executeDelete(String url) {
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String result = null;

        try {
            response = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(entity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求 不带参数
     *
     * @param url 请求地址
     * @return 请求得到的未处理的消息
     */
    public static String executePost(String url) {
        //开始发送请求
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(httpEntity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求 带Map参数
     *
     * @param url 请求地址
     * @return 请求得到的未处理的消息
     */
    public static String executePost(String url, Map<String, String> map) {
        //从Map中提取参数放入List
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        //开始发送请求
        UrlEncodedFormEntity entity = null;
        String result = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(httpEntity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executePut(String url, Map<String, String> map) {
        //从Map中提取参数放入List
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        //开始发送请求
        UrlEncodedFormEntity entity = null;
        String result = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(requestConfig);
            httpPut.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPut);

            HttpEntity httpEntity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(httpEntity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executePut(String url) {
        //开始发送请求
        String result = null;
        try {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpPut);

            HttpEntity httpEntity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(httpEntity, "UTF-8") : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String executePut(String url, Object obj) {
        //Object转Map
        Map<String, String> map = null;
        try {
            map = objectToMap(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BookFrontedCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executePut(url, map);
    }

    /**
     * POST请求 带Object参数
     *
     * @param url 请求地址
     * @return 请求得到的未处理的消息
     */
    public static String executePost(String url, Object obj) {
        //Object转Map
        Map<String, String> map = null;
        try {
            map = objectToMap(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BookFrontedCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executePost(url, map);
    }

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (field.get(obj) != null) {
                String value = field.get(obj).toString();
                map.put(fieldName, value);
            }
        }
        return map;
    }

    public static void main(String args[]) throws IllegalAccessException {
        System.out.println("Hello");
        String url = "https://tomcat.home999.cc/book/User/login";
        User user = new User("R2005001", "", "zh_CN");
        String jsonstring;
        Map<String, String> map = objectToMap(user);
        //jsonstring = postJson(url,user);
        jsonstring = executePost(url, map);
        //jsonstring = executeGet(url);
        Msg message = JSONObject.parseObject(jsonstring, Msg.class);
        System.out.println(message);
    }
}
