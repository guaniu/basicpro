package com.yiding.saas.ydsaas.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 运单烟叶展示层
 */
public class TransportTobaccoVo implements Serializable {

    private static final long serialVersionUID = -7505005262779024406L;
    /**
     * 运单号
     */
    private String transportNo;
    /**
     * 文山扣
     */
    private String rfidId;
    /**
     * 烟叶名称
     */
    private String tobaccoName;
    /**
     * 烟叶级别
     */
    private String tobaccoLevel;
    /**
     * 发货点
     */
    private String tobaccoStation;
    /**
     * 重量
     */
    private String weight;
    /**
     * 打包类型0 烟包 1 烟框
     */
    private String packageType;
    /**
     * 打包日期
     */
    private Date packageDate;

    /**
     * 运单状态：1.待装车 2.待出发 3.在途中 4.待收货 5.已收货
     */
    private String transportState;

    /**
     * 备注
     */
    private String remarks;

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getRfidId() {
        return rfidId;
    }

    public void setRfidId(String rfidId) {
        this.rfidId = rfidId;
    }

    public String getTobaccoName() {
        return tobaccoName;
    }

    public void setTobaccoName(String tobaccoName) {
        this.tobaccoName = tobaccoName;
    }

    public String getTobaccoLevel() {
        return tobaccoLevel;
    }

    public void setTobaccoLevel(String tobaccoLevel) {
        this.tobaccoLevel = tobaccoLevel;
    }

    public String getTobaccoStation() {
        return tobaccoStation;
    }

    public void setTobaccoStation(String tobaccoStation) {
        this.tobaccoStation = tobaccoStation;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Date getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(Date packageDate) {
        this.packageDate = packageDate;
    }

    public String getTransportState() {
        return transportState;
    }

    public void setTransportState(String transportState) {
        this.transportState = transportState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
