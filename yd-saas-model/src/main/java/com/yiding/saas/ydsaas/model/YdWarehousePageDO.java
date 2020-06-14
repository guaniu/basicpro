package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public class YdWarehousePageDO {
    private String inOutType;
    private String rfid;
    private String packageType;//打包类型
    private String tobaccoStation;
    private int status;//0 烟站仓库 1复烤厂仓库
    private String startDate;
    private String endDate;
    private int isValid;
    private Long userId;
    private int  pageNum;
    private int  pageSize;
    private List<String> list;
    private List<String> list2;
    private int stateId;//州 3
    private int cityId;//县 2
    private int townId;//镇 1
    private int repertoryId;//仓库Id ,因仓库建立在组织表中，会出现重复情况，故称为repertoryId

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public int getRepertoryId() {
        return repertoryId;
    }

    public void setRepertoryId(int repertoryId) {
        this.repertoryId = repertoryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }


    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }


    public String getTobaccoStation() {
        return tobaccoStation;
    }

    public void setTobaccoStation(String tobaccoStation) {
        this.tobaccoStation = tobaccoStation;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
