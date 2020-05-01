/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Model.AlterPasswordModel;
import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.Model.UserInfoModel;
import cc.home999.bookfrontend.bean.User;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口控制器
 *
 * @author congcong
 */
public class UserController {

    private static final UserController instance = new UserController();
    private final String baseurl = BookFrontedCon.getBaseurl() + "/User";

    private UserController() {
    }

    public static UserController getInstance() {
        return instance;
    }

    public Msg login(User user) {
        String url = baseurl + "/login";
        String json = BookFrontedCon.executePost(url, user);
        return JSONObject.parseObject(json, Msg.class);
    }

    public Msg logout() {
        String url = baseurl + "/logout";
        String json = BookFrontedCon.executeGet(url);
        return JSONObject.parseObject(json, Msg.class);
    }

    public UserInfoModel userinfo() {
        String url = baseurl + "/userinfo";
        String json = BookFrontedCon.executeGet(url);
        return JSONObject.parseObject(json, UserInfoModel.class);
    }

    public Msg psdalter(AlterPasswordModel info) {
        String url = baseurl + "/psdalter";
        String json = BookFrontedCon.executePost(url, info);
        return JSONObject.parseObject(json, Msg.class);
    }

    public Msg resetpwd(String readerno) {
        String url = baseurl + "/resetpwd";
        Map<String,String> map = new HashMap<String, String>();
        map.put("readerno", readerno);
        String json = BookFrontedCon.executePost(url,map);
        return JSONObject.parseObject(json, Msg.class);
    }
}
