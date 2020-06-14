/**
 * @Title: YzUniqModel.java
 * @Package com.yunzong.orientc.model
 * @Description:(用一句话描述该文件做什么)
 * @author lihh
 * @date 2017-2-15 下午5:36:19
 * @version V1.0
 */
package com.yiding.saas.ydsaas.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lihh
 * @ClassName: YzUniqModel
 * @Description: 主键序列model
 * @date 2017-2-15 下午5:36:19
 */
public class UniqIdModel implements Serializable{
    private static final long serialVersionUID = 8218812509922542032L;
    /*主键*/
    private int id;
    /*分库路由*/
    private int hashRoute;
    /*业务名称*/
    private String uniqName;
    /*原始值*/
    private Long originValue;
    /*当前值*/
    private AtomicLong currentValue;
    /*步长*/
    private int increaseStep;

    private Long maxValue;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqName() {
        return uniqName;
    }

    public void setUniqName(String uniqName) {
        this.uniqName = uniqName;
    }

    public Long getOriginValue() {
        return originValue;
    }

    public void setOriginValue(Long originValue) {
        this.originValue = originValue;
    }

    public int getIncreaseStep() {
        return increaseStep;
    }

    public void setIncreaseStep(int increaseStep) {
        this.increaseStep = increaseStep;
    }


    public AtomicLong getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(AtomicLong currentValue) {
        this.currentValue = currentValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isExpire() {
        return originValue + increaseStep == currentValue.get();
    }


    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getHashRoute() {
        return hashRoute;
    }

    public void setHashRoute(int hashRoute) {
        this.hashRoute = hashRoute;
    }
}
