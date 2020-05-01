/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.home999.bookfrontend.bean;

/**
 *
 * @author congcong
 */
public class User {

    private String username;

    private String password;

    private String role;

    private String locale;

    public User() {
        super();
    }

    public User(String username, String password, String locale) {
        super();
        this.username = username;
        this.password = password;
        this.locale = locale;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", role=" + role + ", locale=" + locale + '}';
    }
}
