package com.yiding.saas.ydsaas.model;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public class YdProductInfoDO {

    private int id;
    private String address;//村组地址集合
    private String districtIds;//地区id集合（id+汉字地址组合，大神创作，小弟甘拜下风）
    private String tabaccoGrower;//烟农
    private String cityName;//州 市/县组合
    private String townName;//镇

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(String districtIds) {
        this.districtIds = districtIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTabaccoGrower() {
        return tabaccoGrower;
    }

    public void setTabaccoGrower(String tabaccoGrower) {
        this.tabaccoGrower = tabaccoGrower;
    }
}
