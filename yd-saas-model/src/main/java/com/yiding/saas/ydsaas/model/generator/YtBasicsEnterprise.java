package com.yiding.saas.ydsaas.model.generator;

import java.math.BigDecimal;

/**
 * ---------------------------
 * 企业基本信息 (YtBasicsEnterprise)         
 * ---------------------------
 * 作者：  saas-generator
 * 时间：  2019-02-20 09:46:56
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class YtBasicsEnterprise {

	/** ID */
	private Integer id;
	/** 企业名称 */
	private String name;
	/** 法定代表人 */
	private String legalperson;
	/** 企业负责人 */
	private String manager;
	/** 负责人联系方式 */
	private String contact;
	/** 注册地址省份 */
	private String province;
	/** 注册地址城市 */
	private String city;
	/** 注册地址区县 */
	private String county;
	/** 注册地址乡镇 */
	private String town;
	/** 注册地址 */
	private String address;
	/** 单位性质 */
	private String natureUnit;
	/** 成立时间 */
	private java.util.Date establishTime;
	/** 注册资本 */
	private BigDecimal capital;
	/** 登记机关 */
	private String authority;
	/** 经营范围 */
	private String scope;
	/** 录入人 */
	private String createBy;
	/** 录入时间 */
	private java.util.Date createDate;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private java.util.Date updateDate;
	/** 备注 */
	private String remarks;
	/** 1-删除标识 0-默认 */
	private String status;
	/** 机构编号 */
	private String officeCode;

    private String uname;

    private String password;

    private String email;
    
    private String openingBank;
    
    private String bankCardNumber;
    
    private Integer identityId;
    
    private Integer bankId;
    
    private Integer type;
    
    private String phoneNumber;
    
    private String postNumber;
    
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalperson() {
		return legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNatureUnit() {
		return natureUnit;
	}

	public void setNatureUnit(String natureUnit) {
		this.natureUnit = natureUnit;
	}

	public java.util.Date getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(java.util.Date establishTime) {
		this.establishTime = establishTime;
	}


	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}


	public Integer getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

}