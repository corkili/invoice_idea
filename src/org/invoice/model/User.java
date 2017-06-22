package org.invoice.model;

import org.invoice.dao.UserDao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
    @Column(name = UserDao.COL_SALT)
    private String salt;
    @Column(name = UserDao.COL_AUTHORITY)
    private int authority;
    @Column(name = UserDao.COL_NAME)
    private String name;
    @Column(name = UserDao.COL_JOB_ID)
    private String jobId;
    @Column(name = UserDao.COL_PHONE)
    private String phone;
    @Column(name = UserDao.COL_EMAIL)
    private String email;

    private String confirmPassword;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Map<String, String>  getNeedValidateUserInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("name", name);
        map.put("job_id", jobId);
        map.put("phone", phone);
        map.put("email", email);
        return map;
    }

    public Map<String, Boolean> getAuthorityMap() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("auth_add_invoice", (authority & Authority.AUTHORITY_ADD_INVOICE_RECORD) != 0);
        map.put("auth_query_invoice", (authority & Authority.AUTHORITY_QUERY_INVOICE_RECORD) != 0);
        map.put("auth_del_invoice", (authority & Authority.AUTHORITY_REMOVE_INVOICE_RECORE) != 0);
        map.put("auth_edit_invoice", (authority & Authority.AUTHORITY_MODIFY_INVOICE_RECORD) != 0);
        map.put("auth_manage_user", (authority & Authority.AUTHORITY_MANAGE_USER) != 0);
        map.put("auth_query_report", (authority & Authority.AUTHORITY_QUERY_INVOICE_ANALYSIS_RESULT) != 0);
        return map;
    }

}
