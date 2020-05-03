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
import org.apache.http.client.methods.HttpUriRequest;

/**
 *
 * @author congcong
 */
public class BookFrontedCon {

    private BookFrontedCon() {
    }       //禁止实例化

    public static final int GET = 0, POST = 1, PUT = 2, DELETE = 3;

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
     * 构造http请求
     *
     * @param url
     * @param Method
     * @return
     */
    private static HttpUriRequest getHttpUriRequest(String url, int Method, HttpEntity entity) {
        if (Method == GET) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            return httpGet;
        } else if (Method == POST) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(entity);
            return httpPost;
        } else if (Method == PUT) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(requestConfig);
            httpPut.setEntity(entity);
            return httpPut;
        } else if (Method == DELETE) {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.setConfig(requestConfig);
            return httpDelete;
        }
        return null;
    }

    /**
     * 根据构造的请求信息，执行请求
     *
     * @param httpUriRequest
     * @return
     */
    private static String execute(HttpUriRequest httpUriRequest) {
        CloseableHttpResponse response = null;
        String result = null;
        try {
            response = httpClient.execute(httpUriRequest);
            HttpEntity entity = response.getEntity();
            int httpCode = response.getStatusLine().getStatusCode();
            result = (httpCode == 200) ? EntityUtils.toString(entity, "UTF-8") : null;
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行指定方法的Http请求 不带参数
     *
     * @param url
     * @param Method
     * @return
     */
    public static String execute(String url, int Method) {
        return execute(getHttpUriRequest(url, Method, null));
    }

    /**
     * 执行指定方法的Http请求 带Map参数
     *
     * @param url
     * @param Method
     * @param map
     * @return
     */
    public static String execute(String url, int Method, Map<String, String> map) {
        //从Map中提取参数放入List
        List<NameValuePair> params = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        //List编码成entity
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return execute(getHttpUriRequest(url, Method, entity));
    }

    /**
     * 执行指定方法的Http请求 带Object参数
     *
     * @param url 请求地址
     * @return 请求得到的未处理的消息
     */
    public static String execute(String url, int Method, Object obj) {
        //Object转Map
        Map<String, String> map = null;
        try {
            map = objectToMap(obj);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BookFrontedCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return execute(url, Method, map);
    }

    /**
     * 利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
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
        jsonstring = execute(url, POST, map);
        //jsonstring = executeGet(url);
        Msg message = JSONObject.parseObject(jsonstring, Msg.class);
        System.out.println(message);
    }
}
