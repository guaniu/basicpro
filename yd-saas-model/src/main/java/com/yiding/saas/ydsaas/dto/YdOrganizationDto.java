package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

/**
 * 传输层 传参
 */
public class YdOrganizationDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 3013688179434694791L;
    /**
     * 组织简称
     */
    private String orgName;
    /**
     * 组织全称
     */
    private String orgFullName;
    /**
     * 状态
     */
    private Integer orgStatus;

    /**
     * 组织类型1:企业2:烟站3:仓库4:复烤厂
     */
    private String orgType;

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

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
}
