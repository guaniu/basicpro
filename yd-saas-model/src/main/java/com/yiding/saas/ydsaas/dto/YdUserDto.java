package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

/**
 * 数据传输层 传参
 */
public class YdUserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -3300331074524552674L;
    private String userName;
    /**
     * 登录账号
     */
    private String userLoginAccount;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 所属组织id
     */
    private Integer userOrgId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLoginAccount() {
        return userLoginAccount;
    }

    public void setUserLoginAccount(String userLoginAccount) {
        this.userLoginAccount = userLoginAccount;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Integer userOrgId) {
        this.userOrgId = userOrgId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 状态:1正常0删除(重要信息假删除）
     */
    private Integer userStatus;


}
