package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 行政区(YdDistrict)实体类
 *
 * @author makejava
 * @since 2020-05-29 17:35:52
 */
public class YdDistrict implements Serializable {
    private static final long serialVersionUID = -84025892868969742L;
    
    private Integer id;
    /**
    * 行政区名称
    */
    private String districtName;
    /**
    * 行政区编码
    */
    private String districtCode;
    
    private Date createTime;
    
    private Date updateTime;
    /**
    * 父id
    */
    private Integer parentId;
    /**
    * 行政区级别
    */
    private String districtLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(String districtLevel) {
        this.districtLevel = districtLevel;
    }

}