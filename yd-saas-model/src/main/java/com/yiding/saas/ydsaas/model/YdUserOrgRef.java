package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户-组织-关联关系用于控制数据权限(YdUserOrgRef)实体类
 *
 * @author makejava
 * @since 2020-05-22 11:04:02
 */
public class YdUserOrgRef implements Serializable {
    private static final long serialVersionUID = 777919845971742823L;
    /**
    * 角色id
    */
    private Long id;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 类型：web：app
    */
    private String type;
    /**
    * 组织id
    */
    private Long orgId;
    /**
    * 创建日期
    */
    private Date createTime;
    /**
    * 修改日期
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}