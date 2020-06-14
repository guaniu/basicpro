package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 组织与收购区域的关系(YdOrgPurchaseRegion)实体类
 *
 * @author makejava
 * @since 2020-06-01 14:29:13
 */
public class YdOrgPurchaseRegion implements Serializable {
    private static final long serialVersionUID = -73467111858350191L;
    
    private Long id;
    /**
    * 组织id
    */
    private Long orgId;
    /**
    * 收购范围村名称
    */
    private String villageName;
    /**
    * 收购范围小组名称
    */
    private String groupName;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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