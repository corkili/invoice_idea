package org.invoice.domain;

import java.io.Serializable;

/**
 * Created by ran on 06/04/17.
 */
public class Authority implements Serializable {
    private static final long serialVersionUID = -773223068322495755L;
    /**
     * 查询发票记录的权限，拥有该权限可以查看系统中存储的发票记录
     */
    public static final int AUTHORITY_QUERY_INVOICE_RECORD          = 0x0001;
    /**
     * 更改发票记录的权限，拥有该权限可以更改发票的信息
     * 前置条件：拥有“查询发票记录的权限”
     */
    public static final int AUTHORITY_MODIFY_INVOICE_RECORD         = 0x0002;
    /**
     * 添加发票记录的权限，拥有该权限可以向数据库中添加发票记录
     */
    public static final int AUTHORITY_ADD_INVOICE_RECORD            = 0x0004;
    /**
     * 删除发票记录的权限，拥有该权限可以删除数据库中的发票记录
     * 前置条件：拥有“发票记录的权限”
     */
    public static final int AUTHORITY_REMOVE_INVOICE_RECORE         = 0x0008;
    /**
     * 查询发票分析结果的权限，拥有该权限可以对使用分析功能，并查看分析结果
     * 前置条件：拥有“查询发票记录的权限”
     */
    public static final int AUTHORITY_QUERY_INVOICE_ANALYSIS_RESULT = 0x0010;

    /**
     * 管理用户的权限，拥有权限的为系统管理员，可以对系统用户进行管理
     */
    public static final int AUTHORITY_MANAGE_USER                   = 0x0020;

    /**
     * 查看系统日志的权限，拥有权限的为系统管理员，可以查看系统的日志
     */
    public static final int AUTHORITY_QUERY_SYSTEM_LOG              = 0x0040;

    private int authority;

    public Authority() {

    }

    public Authority(int authority) {
        setAuthorityIfLegal(authority);
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

    private boolean isLegalAuthority(int authority) {
        if (authority <= getAllAuthority())
            return true;
        return false;
    }

    private int getAllAuthority() {
        return AUTHORITY_QUERY_INVOICE_RECORD
                | AUTHORITY_MODIFY_INVOICE_RECORD
                | AUTHORITY_ADD_INVOICE_RECORD
                | AUTHORITY_REMOVE_INVOICE_RECORE
                | AUTHORITY_QUERY_INVOICE_ANALYSIS_RESULT
                | AUTHORITY_MANAGE_USER
                | AUTHORITY_QUERY_SYSTEM_LOG;
    }

    private void addAuthorityIfLegal(int authority) {
        if (isLegalAuthority(authority))
            this.authority |= authority;
    }

    private void removeAuthorityIfLegal(int authority) {
        if (isLegalAuthority(authority))
            this.authority &= ~authority;
    }

}
