package com.yiding.saas.ydsaas.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 展示层
 */
public class OrganizationVo implements Serializable {
    private static final long serialVersionUID = 3075366185479481517L;
    private Long id;
    private String orgCode;
    private String orgName;
    private String orgFullName;
    private Date updateTime;
    private Long parentId;
    private String orgType;
    private String orgTypeName;
    private Integer orgStatus;
    private String orgStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getOrgStatusName() {
        return orgStatusName;
    }

    public void setOrgStatusName(String orgStatusName) {
        this.orgStatusName = orgStatusName;
    }
}
