package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 传输层
 */
public class TransportTobaccoDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 3670009088488713033L;
    /**
     * 运单id
     */
    private Integer id;
    /**
     * 文山扣
     */
    private String rfid;
    /**
     * 状态
     */
    private Integer travelState;

    /**
     * 运单号
     */
    private String transportNo;

    /**
     * 数据权限
     */
    private List<Long> orgIds;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 设备Id
     */
    private Integer deviceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Integer getTravelState() {
        return travelState;
    }

    public void setTravelState(Integer travelState) {
        this.travelState = travelState;
    }

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
