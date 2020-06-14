package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 传输层
 */
public class YdUserOrgRefDto implements Serializable {
    private static final long serialVersionUID = 674698329781043397L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 组织id
     */
    private List<Long> orgIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Long> orgIds) {
        this.orgIds = orgIds;
    }
}
