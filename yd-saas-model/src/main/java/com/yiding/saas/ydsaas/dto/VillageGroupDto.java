package com.yiding.saas.ydsaas.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 村庄-小组模型
 */
public class VillageGroupDto implements Serializable {
    private static final long serialVersionUID = 7059533029863799375L;

    /**
     * 收购范围村名称
     */
    @Excel(name = "村庄", orderNum = "1", width = 20)
    private String villageName;
    /**
     * 收购范围小组名称
     */
    @Excel(name = "小组", orderNum = "2", width = 20)
    private String groupName;

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
