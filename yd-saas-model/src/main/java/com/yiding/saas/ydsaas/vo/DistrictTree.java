package com.yiding.saas.ydsaas.vo;

import com.yiding.saas.ydsaas.model.YdDistrict;

import java.io.Serializable;
import java.util.List;

/**
 * 行政区树对象
 */
public class DistrictTree implements Serializable {

    private static final long serialVersionUID = -8608004181699249859L;

    private Integer id;
    private String districtName;
    private Integer parentId;
    private String districtLevel;
    private List<DistrictTree> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<DistrictTree> getChildren() {
        return children;
    }

    public void setChildren(List<DistrictTree> children) {
        this.children = children;
    }

    public String getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(String districtLevel) {
        this.districtLevel = districtLevel;
    }

    /**
     * 行政区模型转树模型
     *
     * @param ydDistrict
     * @return
     */
    public static DistrictTree convertDicTree(YdDistrict ydDistrict) {
        DistrictTree districtTree = new DistrictTree();
        districtTree.setId(ydDistrict.getId());
        districtTree.setDistrictName(ydDistrict.getDistrictName());
        districtTree.setParentId(ydDistrict.getParentId());
        districtTree.setDistrictLevel(ydDistrict.getDistrictLevel());
        return districtTree;
    }
}
