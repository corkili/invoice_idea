package org.invoice.model;

import org.invoice.dao.UserDao;

import javax.persistence.*;
import java.io.Serializable;
import static org.invoice.model.Authority.*;
/**
 * Created by ran on 06/04/17.
 */
@Entity
@Table(name = UserDao.TABLE_USER)
public class User implements Serializable {
    private static final long serialVersionUID = 3692706551391721360L;
    @Column(name = UserDao.COL_USER_ID)
    private int userId;
    @Column(name = UserDao.COL_USERNAME)
    private String username;
    @Column(name = UserDao.COL_PASSWORD)
    private String password;
    @Column(name = UserDao.COL_AUTHORITY)
    private int authority;

    public User() {

    }

    @Id
    @GeneratedValue
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        setAuthorityIfLegal(authority);
    }

    public void addAuthority(int authority) {
        addAuthorityIfLegal(authority);
    }

    public void removeAuthority(int authority) {
        removeAuthorityIfLegal(authority);
    }

    private void setAuthorityIfLegal(int authority) {
        if (isLegalAuthority(authority))
            this.authority = authority;
    }

    private void addAuthorityIfLegal(int authority) {
        if (isLegalAuthority(authority))
            this.authority |= authority;
    }

    private void removeAuthorityIfLegal(int authority) {
        if (isLegalAuthority(authority))
            this.authority &= ~authority;
    }

    private boolean isLegalAuthority(int authority) {
        if (authority <= getAllAuthority())
            return true;
        return false;
    }
}
