/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.bean.Reader;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author congcong
 */
public class ReaderController {

    private static ReaderController instance = new ReaderController();
    private final String baseurl = BookFrontedCon.getBaseurl() + "/Reader";

    private ReaderController() {
    }

    public static ReaderController getInstance() {
        return instance;
    }

    /**
     * 读者添加
     *
     * @param reader
     * @param result
     * @return
     */
    public Msg addreader(Reader reader) {
        String url = baseurl;
        String json = BookFrontedCon.execute(url,BookFrontedCon.POST, reader);
        return JSONObject.parseObject(json, Msg.class);
    }

    /**
     * 读者编辑
     *
     * @param reader
     * @return
     */
    public Msg editreader(Reader reader) {
        String url = baseurl;
        String json = BookFrontedCon.execute(url,BookFrontedCon.PUT, reader);
        return JSONObject.parseObject(json, Msg.class);
    }

    /**
     * 读者删除
     *
     * @param readerno
     * @return
     */
    public Msg delreader(String readerno) {
        String url = baseurl + "/" + readerno;
        String json = BookFrontedCon.execute(url,BookFrontedCon.DELETE);
        return JSONObject.parseObject(json, Msg.class);
    }
}
