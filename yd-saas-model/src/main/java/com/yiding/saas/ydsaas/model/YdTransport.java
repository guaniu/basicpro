package com.yiding.saas.ydsaas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yiding.saas.ydsaas.dto.BaseDto;

public class YdTransport extends BaseDto implements Serializable{
	
	private static final long serialVersionUID = 144775757L;

	private Integer id;

    private Integer orgId;

    private Integer sendId;

    private String sendName;

    private Integer receiveId;

    private String receiveName;

    private String deviceNo;

    private String carNo;

    private String dispatchNo;

    private String transportNo;

    private Integer estime;

    private String driverName;

    private String driverMobile;

    /**
     * 运单状态 1.待装车 2.已装车 3.在途中 4.待收货 5.已收货
     */
    private Integer transportState;

    private Date createTime;

    private Date departTime;

    private Date arriveTime;

    private Date finshTime;

    private Date updateTime;

    private Integer status;

    private String remarks;
    
    private Integer loaderId;
    
    private String weight;
    
    private Integer goodsNum;
    
    private String goodsType;
    
    private String parentIds;


    /**********自定义参数************/
    private String appArgsNo;//运单号或车牌号或调度号
    
    private String estimeLabel;//预计时间date格式
    
    private List<String> rfidList;//文山扣集合
    
    private String useTime;//用时
    
    private Integer item; //1.创建时间 2.完成时间 3.到达时间
    
    private String itemBt;//PC查询开始时间
    
    private String itemEt;//PC查询结束时间
    
    private String expectedTime;//PC预计到达时间
    
    private Integer createTimeNow;//1当天数据


    /**
     * 数据权限
     */
    private List<Long> orgIds;

    private List<String> orgStrIds;

    public List<String> getOrgStrIds() {
        return orgStrIds;
    }

    public void setOrgStrIds(List<String> orgStrIds) {
        this.orgStrIds = orgStrIds;
    }

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getSendId() {
        return sendId;
    }

    public void setSendId(Integer sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
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

    public Integer getEstime() {
        return estime;
    }

    public void setEstime(Integer estime) {
        this.estime = estime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    

	public Integer getLoaderId() {
		return loaderId;
	}

	public void setLoaderId(Integer loaderId) {
		this.loaderId = loaderId;
	}
	

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
    public String getAppArgsNo() {
		return appArgsNo;
	}

	public void setAppArgsNo(String appArgsNo) {
		this.appArgsNo = appArgsNo;
	}

	public String getEstimeLabel() {
		return estimeLabel;
	}

	public void setEstimeLabel(String estimeLabel) {
		this.estimeLabel = estimeLabel;
	}

	public List<String> getRfidList() {
		return rfidList;
	}

	public void setRfidList(List<String> rfidList) {
		this.rfidList = rfidList;
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

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getItemBt() {
		return itemBt;
	}

	public void setItemBt(String itemBt) {
		this.itemBt = itemBt;
	}

	public String getItemEt() {
		return itemEt;
	}

	public void setItemEt(String itemEt) {
		this.itemEt = itemEt;
	}

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public Integer getCreateTimeNow() {
		return createTimeNow;
	}

	public void setCreateTimeNow(Integer createTimeNow) {
		this.createTimeNow = createTimeNow;
	}

	@Override
	public String toString() {
		return "YdTransport [id=" + id + ", orgId=" + orgId + ", sendId=" + sendId + ", sendName=" + sendName
				+ ", receiveId=" + receiveId + ", receiveName=" + receiveName + ", deviceNo=" + deviceNo + ", carNo="
				+ carNo + ", dispatchNo=" + dispatchNo + ", transportNo=" + transportNo + ", estime=" + estime
				+ ", driverName=" + driverName + ", driverMobile=" + driverMobile + ", transportState=" + transportState
				+ ", createTime=" + createTime + ", departTime=" + departTime
				+ ", arriveTime=" + arriveTime + ", finshTime=" + finshTime + ", updateTime=" + updateTime + ", status="
				+ status + ", remarks=" + remarks + ", loaderId=" + loaderId + ", weight=" + weight + ", goodsNum="
				+ goodsNum + ", goodsType=" + goodsType + ", parentIds=" + parentIds + ", appArgsNo=" + appArgsNo
				+ ", estimeLabel=" + estimeLabel + ", rfidList=" + rfidList + ", useTime=" + useTime + ", item=" + item
				+ "]";
	}

}