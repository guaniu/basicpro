package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 组织机构(YdOrganization)实体类
 *
 * @author makejava
 * @since 2020-06-03 11:41:58
 */
public class YdOrganization implements Serializable {
    private static final long serialVersionUID = 689517132765719646L;
    
    private Long id;
    /**
    * 组织名称
    */
    private String orgName;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 父id
    */
    private Long parentId;
    /**
    * 全称
    */
    private String orgFullName;
    /**
    * 排序字段
    */
    private Integer orderNum;
    /**
    * 企业编码
    */
    private String orgCode;
    /**
    * 组织类型1:企业2:烟站3:复烤厂4:仓库
    */
    private String orgType;
    /**
    * 归属区域id
    */
    private Integer orgDistrictId;
    /**
    * 经度
    */
    private String orgLongitude;
    /**
    * 纬度
    */
    private String orgLatitude;
    /**
    * 公司具体地址
    */
    private String orgAddress;
    /**
    * 电话
    */
    private String orgTele;
    /**
    * 公司负责人id
    */
    private Long officalUserId;
    /**
    * 公司负责人名称
    */
    private String officalUserName;
    /**
    * 组织备注信息
    */
    private Object orgComments;
    /**
    * 状态:1正常0停用
    */
    private Integer orgStatus;
    /**
    * 0:未删除1:删除
    */
    private Integer isDel;


    /**
     * 仓库名称
     */
    private String storehouseName;

    /**
    * 仓库容积
    */
    private String storehouseVolume;

    /**
     * 收购区域
     */

    private List<YdOrgPurchaseRegion> orgPurchaseRegions;

    /**
     * 仓库信息
     */
    private List<YdOrganization> orgStorehouses;


    /**
     * 区域Ids
     */
    private List<Integer> districtIds;

    public List<Integer> getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(List<Integer> districtIds) {
        this.districtIds = districtIds;
    }

    public List<YdOrganization> getOrgStorehouses() {
        return orgStorehouses;
    }

    public void setOrgStorehouses(List<YdOrganization> orgStorehouses) {
        this.orgStorehouses = orgStorehouses;
    }

    public List<YdOrgPurchaseRegion> getOrgPurchaseRegions() {
        return orgPurchaseRegions;
    }

    public void setOrgPurchaseRegions(List<YdOrgPurchaseRegion> orgPurchaseRegions) {
        this.orgPurchaseRegions = orgPurchaseRegions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgDistrictId() {
        return orgDistrictId;
    }

    public void setOrgDistrictId(Integer orgDistrictId) {
        this.orgDistrictId = orgDistrictId;
    }

    public String getOrgLongitude() {
        return orgLongitude;
    }

    public void setOrgLongitude(String orgLongitude) {
        this.orgLongitude = orgLongitude;
    }

    public String getOrgLatitude() {
        return orgLatitude;
    }

    public void setOrgLatitude(String orgLatitude) {
        this.orgLatitude = orgLatitude;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgTele() {
        return orgTele;
    }

    public void setOrgTele(String orgTele) {
        this.orgTele = orgTele;
    }

    public Long getOfficalUserId() {
        return officalUserId;
    }

    public void setOfficalUserId(Long officalUserId) {
        this.officalUserId = officalUserId;
    }

    public String getOfficalUserName() {
        return officalUserName;
    }

    public void setOfficalUserName(String officalUserName) {
        this.officalUserName = officalUserName;
    }

    public Object getOrgComments() {
        return orgComments;
    }

    public void setOrgComments(Object orgComments) {
        this.orgComments = orgComments;
    }

    public Integer getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Integer orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getStorehouseVolume() {
        return storehouseVolume;
    }

    public void setStorehouseVolume(String storehouseVolume) {
        this.storehouseVolume = storehouseVolume;
    }

    public String getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(String storehouseName) {
        this.storehouseName = storehouseName;
    }
}