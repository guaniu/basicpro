package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/6/1
 */
public class YdWarehouseDO {
    private int id;
    private int orgId;
    private int districtId;
    private String orgIds;
    private int tobaccoId;//烟叶信息id
    private String packageType;//打包类型
    private String packageDate;//打包类型
    private String tobaccoStation;
    private int status;//0 烟站仓库 1复烤厂仓库
    private String createBy;
    private String createDate;
    private int isValid;
    private String tobaccoName;
    private String tobaccoLevel;
    private String weightOne;//当前等级的库存量（kg）
    private String weightTwo;//当前等级的库存量（担）
    private String unitOne;
    private String unitTwo;
    private String capacity;//仓库总容量
    private String totalWeight;//仓库总重量
    private String capacityPercent;//库容占比
    private double warehousePercent;//库存占比
    private double count;//数量
    private List<YdWarehousePercentDO> list;//库存占比列表
    private int istoday;//是否当天  1当天 2当年
    private String inOutType; //出入库类型 0入库 1出库
    
    
    public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public int getIstoday() {
		return istoday;
	}

	public void setIstoday(int istoday) {
		this.istoday = istoday;
	}

	public List<YdWarehousePercentDO> getList() {
        return list;
    }

    public void setList(List<YdWarehousePercentDO> list) {
        this.list = list;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getWarehousePercent() {
        return warehousePercent;
    }

    public void setWarehousePercent(double warehousePercent) {
        this.warehousePercent = warehousePercent;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public int getTobaccoId() {
        return tobaccoId;
    }

    public void setTobaccoId(int tobaccoId) {
        this.tobaccoId = tobaccoId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(String packageDate) {
        this.packageDate = packageDate;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getTobaccoName() {
        return tobaccoName;
    }

    public void setTobaccoName(String tobaccoName) {
        this.tobaccoName = tobaccoName;
    }

    public String getTobaccoLevel() {
        return tobaccoLevel;
    }

    public void setTobaccoLevel(String tobaccoLevel) {
        this.tobaccoLevel = tobaccoLevel;
    }

    public String getWeightOne() {
        return weightOne;
    }

    public void setWeightOne(String weightOne) {
        this.weightOne = weightOne;
    }

    public String getWeightTwo() {
        return weightTwo;
    }

    public void setWeightTwo(String weightTwo) {
        this.weightTwo = weightTwo;
    }

    public String getUnitOne() {
        return unitOne;
    }

    public void setUnitOne(String unitOne) {
        this.unitOne = unitOne;
    }

    public String getUnitTwo() {
        return unitTwo;
    }

    public void setUnitTwo(String unitTwo) {
        this.unitTwo = unitTwo;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacityPercent() {
        return capacityPercent;
    }

    public void setCapacityPercent(String capacityPercent) {
        this.capacityPercent = capacityPercent;
    }
}
