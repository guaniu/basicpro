package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 告警表(YdWarn)实体类
 *
 * @author makejava
 * @since 2020-06-05 10:44:42
 */
public class YdWarn implements Serializable {
    private static final long serialVersionUID = -70918945705005664L;
    
    private Long id;
    /**
    * 告警类型:1:停车超时2:低电量3:丢失4:拆卸
    */
    private String warnType;
    /**
    * 运单Id
    */
    private Integer transportId;
    /**
    * 车辆经度
    */
    private String carLongitude;
    /**
    * 车辆维度
    */
    private String carLatitude;
    /**
    * 告警内容
    */
    private String warnContent;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 处理状态:0未处理1:已处理
    */
    private Integer processState;
    /**
    * 处理人id
    */
    private Long processUserId;
    /**
    * 处理人名称
    */
    private String processUserName;
    /**
    * 处理结果说明
    */
    private String processContent;
    
    //非数据库参数
    /**
     * 告警名称
     */
    private String warnTypeName;
    /**
     * 处理状态名称
     */
    private String processStateName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getCarLongitude() {
        return carLongitude;
    }

    public void setCarLongitude(String carLongitude) {
        this.carLongitude = carLongitude;
    }

    public String getCarLatitude() {
        return carLatitude;
    }

    public void setCarLatitude(String carLatitude) {
        this.carLatitude = carLatitude;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
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

    public Integer getProcessState() {
        return processState;
    }

    public void setProcessState(Integer processState) {
        this.processState = processState;
    }

    public Long getProcessUserId() {
        return processUserId;
    }

    public void setProcessUserId(Long processUserId) {
        this.processUserId = processUserId;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
    }

    public String getProcessContent() {
        return processContent;
    }

    public void setProcessContent(String processContent) {
        this.processContent = processContent;
    }

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

}