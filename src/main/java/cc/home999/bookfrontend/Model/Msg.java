/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.Model;

/**
 *
 * @author congcong
 */
public class Msg {
    //状态码  100-成功 200-失败
    private int code;
    //提示信息
    private String message;

    public static Msg success(String message) {
        Msg result = new Msg();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    public static Msg fail(String message) {
        Msg result = new Msg();
        result.setCode(403);
        result.setMessage(message);
        return result;
    }

    public Msg() {
        super();
    }

    public Msg(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Msg{" + "code=" + code + ", message=" + message + '}';
    }

}
