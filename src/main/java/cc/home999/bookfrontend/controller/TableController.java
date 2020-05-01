/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Model.TableModel;
import cc.home999.bookfrontend.bean.Book;
import cc.home999.bookfrontend.bean.Reader;
import cc.home999.bookfrontend.Model.BookReaderModel;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author congcong
 */
public class TableController {

    private static TableController instance = new TableController();
    private final String baseurl = BookFrontedCon.getBaseurl() + "/Table";

    private TableController() {
    }

    public static TableController getInstance() {
        return instance;
    }

    /**
     * 获取图书Table
     *
     * @param book
     * @return
     */
    public TableModel bookadmins(Book book) {
        String url = baseurl + "/bookadmins";
        String json = BookFrontedCon.executePost(url, book);
        return JSONObject.parseObject(json, TableModel.class);
    }

    /**
     * 获取读者Table
     *
     * @param Reader
     * @return
     */
    public TableModel readeradmins(Reader reader) {
        String url = baseurl + "/readeradmins";
        String json = BookFrontedCon.executePost(url, reader);
        return JSONObject.parseObject(json, TableModel.class);
    }

    /**
     * 获取读者借阅信息Table
     *
     * @param Reader
     * @return
     */
    public TableModel borrowreaders(String table) {
        String url = baseurl + "/borrowreaders";
        Map<String,String> map = new HashMap<String, String>();
        map.put("table", table);
        String json = BookFrontedCon.executePost(url, map);
        return JSONObject.parseObject(json, TableModel.class);
    }

    /**
     * 获取读者看到的图书Table
     *
     * @param Reader
     * @return
     */
    public TableModel bookreaders(BookReaderModel bookReader) {
        String url = baseurl + "/bookreaders";
        String json = BookFrontedCon.executePost(url, bookReader);
        return JSONObject.parseObject(json, TableModel.class);
    }
}
