package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

/**
 * 行政区传输层
 */
public class YdDistrictDto implements Serializable {
    private static final long serialVersionUID = -7464678501152763178L;
    /**
     * 行政区ID
     */
    private Integer id;
    /**
     * 行政区父级ID
     */
    private Integer parentId;


    /**
     * 行政区级别(city:市,district:县,street:乡镇)
     */
    private String districtLevel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(String districtLevel) {
        this.districtLevel = districtLevel;
    }
}
