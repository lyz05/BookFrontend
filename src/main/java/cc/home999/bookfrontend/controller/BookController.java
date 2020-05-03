/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.bean.Book;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author congcong
 */
public class BookController {

    private static BookController instance = new BookController();
    private final String baseurl = BookFrontedCon.getBaseurl() + "/Book";

    private BookController() {
    }

    public static BookController getInstance() {
        return instance;
    }

    /**
     * 图书添加 Post
     *
     * @param book
     * @param result
     * @return
     */
    public Msg addbook(Book book) {
        String url = baseurl;
        String json = BookFrontedCon.execute(url,BookFrontedCon.POST, book);
        return JSONObject.parseObject(json, Msg.class);
    }

    /**
     * 图书编辑 PUT
     *
     * @param book
     * @param result
     * @return
     */
    public Msg editbook(Book book) {
        String url = baseurl;
        String json = BookFrontedCon.execute(url,BookFrontedCon.PUT, book);
        return JSONObject.parseObject(json, Msg.class);
    }

    /**
     * 图书删除 DELETE
     *
     * @param bookno
     * @return
     */
    public Msg delbook(String bookno) {
        String url = baseurl + "/" + bookno;
        String json = BookFrontedCon.execute(url,BookFrontedCon.DELETE);
        return JSONObject.parseObject(json, Msg.class);
    }
}
