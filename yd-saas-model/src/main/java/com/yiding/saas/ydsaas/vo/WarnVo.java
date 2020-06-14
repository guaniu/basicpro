package com.yiding.saas.ydsaas.vo;

import com.yiding.saas.ydsaas.model.YdWarn;

import java.io.Serializable;

/**
 * 告警视图层
 */
public class WarnVo extends YdWarn implements Serializable {
    private static final long serialVersionUID = -3934948405181479484L;
    /**
     * 告警名称
     */
    private String warnTypeName;
    /**
     * 处理状态名称
     */
    private String processStateName;

    /**
     * 运单号
     */
    private String transprotNo;

    /**
     * 司机
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverMobile;

    /**
     * 车牌号
     */
    private String carNo;


    public String getWarnTypeName() {
        return warnTypeName;
    }

    public void setWarnTypeName(String warnTypeName) {
        this.warnTypeName = warnTypeName;
    }

    public String getProcessStateName() {
        return processStateName;
    }

    public void setProcessStateName(String processStateName) {
        this.processStateName = processStateName;
    }

    public String getTransprotNo() {
        return transprotNo;
    }

    public void setTransprotNo(String transprotNo) {
        this.transprotNo = transprotNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "WarnVo{" +
                "warnTypeName='" + warnTypeName + '\'' +
                ", processStateName='" + processStateName + '\'' +
                ", transprotNo='" + transprotNo + '\'' +
                ", driverName='" + driverName + '\'' +
                ", driverMobile='" + driverMobile + '\'' +
                ", carNo='" + carNo + '\'' +
                '}';
    }
}
