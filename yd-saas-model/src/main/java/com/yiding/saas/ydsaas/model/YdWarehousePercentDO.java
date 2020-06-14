package com.yiding.saas.ydsaas.model;

import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/6/8
 */
public class YdWarehousePercentDO {
    private int repertoryId;//仓库id
    private String repertoryName;//仓库名称和站点拼接组合
    private String tobaccoLevel;
    private String tobaccoStation;
    private double totalWeight;
    private List list;

    public String getTobaccoStation() {
        return tobaccoStation;
    }

    public void setTobaccoStation(String tobaccoStation) {
        this.tobaccoStation = tobaccoStation;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getRepertoryId() {
        return repertoryId;
    }

    public void setRepertoryId(int repertoryId) {
        this.repertoryId = repertoryId;
    }

    public String getRepertoryName() {
        return repertoryName;
    }

    public void setRepertoryName(String repertoryName) {
        this.repertoryName = repertoryName;
    }

    public String getTobaccoLevel() {
        return tobaccoLevel;
    }

    public void setTobaccoLevel(String tobaccoLevel) {
        this.tobaccoLevel = tobaccoLevel;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }


}
