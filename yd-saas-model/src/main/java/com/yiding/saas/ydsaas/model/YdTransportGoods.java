package com.yiding.saas.ydsaas.model;

import java.io.Serializable;
import java.util.Date;

import com.yiding.saas.ydsaas.dto.BaseDto;

public class YdTransportGoods extends BaseDto implements Serializable{
	
	private static final long serialVersionUID = 954225565777L;

	private Integer id;

    private Integer transportId;

    private String rfid;

    private Date createTime;
    
    private Integer goodsState;
    
    private String goodsStateLabel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsStateLabel() {
		return goodsStateLabel;
	}

	public void setGoodsStateLabel(String goodsStateLabel) {
		this.goodsStateLabel = goodsStateLabel;
	}

	@Override
	public String toString() {
		return "YdTransportGoods [id=" + id + ", transportId=" + transportId + ", rfid=" + rfid + ", createTime="
				+ createTime + ", goodsState=" + goodsState + ", goodsStateLabel=" + goodsStateLabel + "]";
	}

    
}