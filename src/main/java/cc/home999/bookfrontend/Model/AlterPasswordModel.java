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
public class AlterPasswordModel {

    private String username;
    private String pwd;
    private String newpwd;
    private String newpwd2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpwd2() {
        return newpwd2;
    }

    public void setNewpwd2(String newpwd2) {
        this.newpwd2 = newpwd2;
    }

    public AlterPasswordModel(String username, String pwd, String newpwd, String newpwd2) {
        this.username = username;
        this.pwd = pwd;
        this.newpwd = newpwd;
        this.newpwd2 = newpwd2;
    }
    
}
