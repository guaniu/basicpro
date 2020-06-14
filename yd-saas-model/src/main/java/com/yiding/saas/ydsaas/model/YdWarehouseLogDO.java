package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public class YdWarehouseLogDO {

    private int id;
    private int orgId;
    private int repertoryId;
    private long userId;
    private int districtId;//烟站的地区id
    private String districtIds;//烟草产地信息的地区id集合，用-分割
    private String orgIds;
    private Date inOutDate;
    private String inOutType;
    private String rfid;
    private String variety;//品种
    private int tobaccoId;//烟叶信息id
    private String packageType;//打包类型
    private String packageDate;//打包类型
    private double count;//出入库数量
    private String unit;//出入库数量
    private String weight;//仓库明细表的kg重量
    private String weightLog;//仓库明细表的担重量，也是前端传参的净重
    private String tobaccoStation;
    private String repetTobaccoStation;
    private int status;//0 烟站仓库 1复烤厂仓库
    private int inOutStatus;//出入库状态0出库1入库
    private String sugar;
    private String nicotine;
    private String nitrogen;
    private String protein;
    private String createBy;
    private String createDate;
    private int isValid;
    private String tobaccoName;
    private String tobaccoLevel;
    private List<YdProductInfoDO> list;
    private String weightOne;//库存表的kg重量
    private String weightTwo;//库存表的担重量
    private String unitOne;
    private String unitTwo;
    private String capacity;
    private List list2;
    private String startDate;
    private String endDate;
    private int goodsState;//物品状态
    private String goodsStateLabel;//物品状态label
    private String address;//产地

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(String districtIds) {
        this.districtIds = districtIds;
    }

    public int getRepertoryId() {
        return repertoryId;
    }

    public void setRepertoryId(int repertoryId) {
        this.repertoryId = repertoryId;
    }

    public String getWeightLog() {
        return weightLog;
    }

    public void setWeightLog(String weightLog) {
        this.weightLog = weightLog;
    }

    public List getList2() {
        return list2;
    }

    public void setList2(List list2) {
        this.list2 = list2;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }

    public List<YdProductInfoDO> getList() {
        return list;
    }

    public String getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(String packageDate) {
        this.packageDate = packageDate;
    }

    public void setList(List<YdProductInfoDO> list) {
        this.list = list;
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

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getInOutDate() {
        return inOutDate;
    }

    public void setInOutDate(Date inOutDate) {
        this.inOutDate = inOutDate;
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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTobaccoStation() {
        return tobaccoStation;
    }

    public void setTobaccoStation(String tobaccoStation) {
        this.tobaccoStation = tobaccoStation;
    }

    public String getRepetTobaccoStation() {
        return repetTobaccoStation;
    }

    public void setRepetTobaccoStation(String repetTobaccoStation) {
        this.repetTobaccoStation = repetTobaccoStation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getInOutStatus() {
        return inOutStatus;
    }

    public void setInOutStatus(int inOutStatus) {
        this.inOutStatus = inOutStatus;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getNicotine() {
        return nicotine;
    }

    public void setNicotine(String nicotine) {
        this.nicotine = nicotine;
    }

    public String getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(String nitrogen) {
        this.nitrogen = nitrogen;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
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

	public int getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(int goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsStateLabel() {
		return goodsStateLabel;
	}

	public void setGoodsStateLabel(String goodsStateLabel) {
		this.goodsStateLabel = goodsStateLabel;
	}
    
}
