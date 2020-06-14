package com.yiding.saas.ydsaas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 烟叶信息(YdTobacco)实体类
 *
 * @author makejava
 * @since 2020-05-20 09:45:42
 */
public class YdTobacco implements Serializable {
    private static final long serialVersionUID = 302271976340324402L;

    private Long id;
    /**
     * 烟叶名称
     */
    private String tobaccoName;
    /**
     * 烟叶等级
     */
    private String tobaccoLevel;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}