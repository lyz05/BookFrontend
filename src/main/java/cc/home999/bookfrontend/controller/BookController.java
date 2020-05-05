/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Frm.FrmLogin;
import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.bean.Book;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author congcong
 */
public class BookController {

    private final Logger logger = LogManager.getLogger(this.getClass());
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
        logger.info("Add Book：" + book);
        String url = baseurl;
        String json = BookFrontedCon.execute(url, BookFrontedCon.POST, book);
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
        logger.info("Edit Book:" + book);
        String url = baseurl;
        String json = BookFrontedCon.execute(url, BookFrontedCon.PUT, book);
        return JSONObject.parseObject(json, Msg.class);
    }

    /**
     * 图书删除 DELETE
     *
     * @param bookno
     * @return
     */
    public Msg delbook(String bookno) {
        logger.info("Delte Book：bookno=" + bookno);
        String url = baseurl + "/" + bookno;
        String json = BookFrontedCon.execute(url, BookFrontedCon.DELETE);
        return JSONObject.parseObject(json, Msg.class);
    }
}
