package org.invoice.domain;

import java.io.Serializable;

/**
 * Created by ran on 06/04/17.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3692706551391721360L;
    private String id;
    private String userName;
    private String password;
    private Authority authority;


    public User() {

    }

    public User(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public User(String id, String userName, String password, Authority authority) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
