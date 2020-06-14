package com.yiding.saas.ydsaas.model;

import java.util.Date;
import java.io.Serializable;

/**
 * 运单日志表(YdTransportLog)实体类
 *
 * @author makejava
 * @since 2020-05-29 13:37:51
 */
public class YdTransportLog implements Serializable {
    private static final long serialVersionUID = -11090580623004401L;
    /**
    * 主键id
    */
    private Integer id;
    /**
    * 运单id
    */
    private Integer transportId;
    /**
    * 录入时间
    */
    private Date createTime;
    /**
    * 日志信息
    */
    private String msg;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}