package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.util.List;

import com.yiding.saas.ydsaas.dto.BaseDto;

public class YdInformation extends BaseDto{
    private Integer id;

    private Integer transportId;

    private Integer type;
    
    private String typeLabel;

    private String content;

    private Date createTime;

    private Integer isRead;

    private Integer status;
    
    private String parentIds;
    
    /**
     * 数据权限
     */
    private List<Long> orgIds;

    private List<String> orgStrIds;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public List<Long> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<Long> orgIds) {
		this.orgIds = orgIds;
	}

	public List<String> getOrgStrIds() {
		return orgStrIds;
	}

	public void setOrgStrIds(List<String> orgStrIds) {
		this.orgStrIds = orgStrIds;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}
	
}