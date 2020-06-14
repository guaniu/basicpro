package com.yiding.saas.ydsaas.dto;

import java.util.Date;

public class YdTransportDto {
	
	private Integer id;

    private String sendName;
    
    private String sendTele;
    
    private String sendAddress;
    
    private String sendLoc_lon;
    
    private String sendLoc_lat;
    
    private String receiveName;
    
    private String receiveTele;
    
    private String receiveAddress;
    
    private String receiveLoc_lon;
    
    private String receiveLoc_lat;

    private String deviceNo;

    private String carNo;

    private String dispatchNo;

    private String transportNo;

    private String driverName;

    private String driverMobile;

    private Integer transportState;

    private Date departTime;

    private String expectedTime;//预计到达时间
    
    private String weight;
    
    private Integer goodsNum;
    
    private String goodsType;
    
    private String useTime;//用时
    
    private Integer estime;
    
    private String estimeLabel;//预计时间date格式
    
    private Date createTime;
    
    private int warnCount;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
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

    public Integer getTransportState() {
        return transportState;
    }

    public void setTransportState(Integer transportState) {
        this.transportState = transportState;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getSendTele() {
		return sendTele;
	}

	public void setSendTele(String sendTele) {
		this.sendTele = sendTele;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getReceiveTele() {
		return receiveTele;
	}

	public void setReceiveTele(String receiveTele) {
		this.receiveTele = receiveTele;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public Integer getEstime() {
		return estime;
	}

	public void setEstime(Integer estime) {
		this.estime = estime;
	}

	public String getEstimeLabel() {
		return estimeLabel;
	}

	public void setEstimeLabel(String estimeLabel) {
		this.estimeLabel = estimeLabel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSendLoc_lon() {
		return sendLoc_lon;
	}

	public void setSendLoc_lon(String sendLoc_lon) {
		this.sendLoc_lon = sendLoc_lon;
	}

	public String getSendLoc_lat() {
		return sendLoc_lat;
	}

	public void setSendLoc_lat(String sendLoc_lat) {
		this.sendLoc_lat = sendLoc_lat;
	}

	public String getReceiveLoc_lon() {
		return receiveLoc_lon;
	}

	public void setReceiveLoc_lon(String receiveLoc_lon) {
		this.receiveLoc_lon = receiveLoc_lon;
	}

	public String getReceiveLoc_lat() {
		return receiveLoc_lat;
	}

	public void setReceiveLoc_lat(String receiveLoc_lat) {
		this.receiveLoc_lat = receiveLoc_lat;
	}

	public int getWarnCount() {
		return warnCount;
	}

	public void setWarnCount(int warnCount) {
		this.warnCount = warnCount;
	}

	@Override
	public String toString() {
		return "YdTransportDto [id=" + id + ", sendName=" + sendName + ", sendTele=" + sendTele + ", sendAddress="
				+ sendAddress + ", sendLoc_lon=" + sendLoc_lon + ", sendLoc_lat=" + sendLoc_lat + ", receiveName="
				+ receiveName + ", receiveTele=" + receiveTele + ", receiveAddress=" + receiveAddress
				+ ", receiveLoc_lon=" + receiveLoc_lon + ", receiveLoc_lat=" + receiveLoc_lat + ", deviceNo=" + deviceNo
				+ ", carNo=" + carNo + ", dispatchNo=" + dispatchNo + ", transportNo=" + transportNo + ", driverName="
				+ driverName + ", driverMobile=" + driverMobile + ", transportState=" + transportState + ", departTime="
				+ departTime + ", expectedTime=" + expectedTime + ", weight=" + weight + ", goodsNum=" + goodsNum
				+ ", goodsType=" + goodsType + ", useTime=" + useTime + ", estime=" + estime + ", estimeLabel="
				+ estimeLabel + ", createTime=" + createTime + "]";
	}

}