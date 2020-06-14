package com.yiding.saas.ydsaas.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户展示层
 */
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2914083388188957393L;

    private Long id;
    /**
     * 用户姓名
     */
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
    /**
     * 所属组织名称(冗余)
     */
    private String userOrgName;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 状态:1正常0停用
     */
    private Integer userStatus;
    /**
     * 角色id
     */
    private Long roleId;
    private String roleName;
    private String userStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUserOrgName() {
        return userOrgName;
    }

    public void setUserOrgName(String userOrgName) {
        this.userOrgName = userOrgName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }
}
