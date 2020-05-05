package cc.home999.bookfrontend.controller;

import cc.home999.bookfrontend.Model.Msg;
import cc.home999.bookfrontend.utils.BookFrontedCon;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BorrowController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private static BorrowController instance = new BorrowController();
    private final String baseurl = BookFrontedCon.getBaseurl() + "/Borrow";

    private BorrowController() {
        
    }

    public static BorrowController getInstance() {
        return instance;
    }

    public Msg addborrow(String bookno) {
        logger.info("Borrow a Book：bookno=" + bookno);
        String url = baseurl + "/" + bookno;
        String json = BookFrontedCon.execute(url, BookFrontedCon.POST);
        return JSONObject.parseObject(json, Msg.class);
    }

    public Msg retborrow(String bookno) {
        logger.info("Return a Book：bookno=" + bookno);
        String url = baseurl + "/" + bookno;
        String json = BookFrontedCon.execute(url, BookFrontedCon.DELETE);
        return JSONObject.parseObject(json, Msg.class);
    }

    public Msg renewborrow(String bookno) {
        logger.info("Renew a Book：bookno=" + bookno);
        String url = baseurl + "/" + bookno;
        String json = BookFrontedCon.execute(url, BookFrontedCon.PUT);
        return JSONObject.parseObject(json, Msg.class);
    }
}
