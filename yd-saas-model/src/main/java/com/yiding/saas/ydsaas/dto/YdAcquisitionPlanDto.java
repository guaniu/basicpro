package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;


/**
 * 传输层
 */
public class YdAcquisitionPlanDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -634003227812435686L;

    private String startTime;
    private String stopTime;
    private Long createUserId;
    private Long storehouseId;
    private String acquisitionWeight;
    private String smokeStationName;
    private Long id;
    private Long smokeStationId;

    public Long getSmokeStationId() {
        return smokeStationId;
    }

    public void setSmokeStationId(Long smokeStationId) {
        this.smokeStationId = smokeStationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmokeStationName() {
        return smokeStationName;
    }

    public void setSmokeStationName(String smokeStationName) {
        this.smokeStationName = smokeStationName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getAcquisitionWeight() {
        return acquisitionWeight;
    }

    public void setAcquisitionWeight(String acquisitionWeight) {
        this.acquisitionWeight = acquisitionWeight;
    }
}
