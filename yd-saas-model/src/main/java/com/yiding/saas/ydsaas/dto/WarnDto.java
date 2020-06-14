package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

public class WarnDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -5585980792325971649L;
    /**
     * 运单编号
     */
    private String transportNo;

    /**
     * 开始日期
     */
    private String startTime;

    /**
     * 截止日期
     */
    private String endTime;


    /**
     * 运单Id
     */
    private Integer transportId;


    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }
}
