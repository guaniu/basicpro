package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

/**
 * 分页
 */
public class BaseDto implements Serializable {
    private static final long serialVersionUID = -7202646383773133708L;
    private Long userId;
    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;
    
    /**
     * 组织机构id
     */
    private Integer orgId;

    public Integer getPageNum() {
        pageNum = pageNum == null ? 1 : pageNum;
        if (pageNum <= 1) {
            pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    @Override
	public String toString() {
		return "BaseDto [pageNum=" + pageNum + ", pageSize=" + pageSize + ", orgId=" + orgId + "]";
	}

	public Integer getPageSize() {
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageSize <= 1) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
