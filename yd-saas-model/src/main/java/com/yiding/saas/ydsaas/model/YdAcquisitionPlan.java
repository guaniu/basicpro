package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 收购计划(YdAcquisitionPlan)实体类
 *
 * @author makejava
 * @since 2020-06-09 17:18:27
 */
public class YdAcquisitionPlan implements Serializable {
    private static final long serialVersionUID = -45743314897812641L;
    
    private Long id;
    /**
    * 组织id(这里只有仓库)
    */
    private Long storehouseId;
    /**
    * 仓库名称(冗余)
    */
    private String storehouseName;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 计划开始时间
    */
    private Date startTime;
    /**
    * 计划截止时间
    */
    private Date stopTime;
    /**
    * 计划年收购数量(kg)
    */
    private String acquisitionWeight;
    /**
    * 创建人id
    */
    private Long createUserId;
    /**
    * 创建人名称(冗余)
    */
    private String createUserName;
    /**
    * 烟站名称
    */
    private String smokeStationName;
    /**
    * 烟站id
    */
    private Long smokeStationId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(String storehouseName) {
        this.storehouseName = storehouseName;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getAcquisitionWeight() {
        return acquisitionWeight;
    }

    public void setAcquisitionWeight(String acquisitionWeight) {
        this.acquisitionWeight = acquisitionWeight;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getSmokeStationName() {
        return smokeStationName;
    }

    public void setSmokeStationName(String smokeStationName) {
        this.smokeStationName = smokeStationName;
    }

    public Long getSmokeStationId() {
        return smokeStationId;
    }

    public void setSmokeStationId(Long smokeStationId) {
        this.smokeStationId = smokeStationId;
    }

}