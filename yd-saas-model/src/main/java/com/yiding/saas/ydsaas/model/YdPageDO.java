package com.yiding.saas.ydsaas.model;

import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public class YdPageDO {

    private int pageNum;
    private int pageSize;
    private int totalSize;
    private int totalPage;
    private double totalWeight;
    private double inOutFrameCount;
    private double inOutPageCount;
    private List<YdWarehouseLogDO> o;
    private List<YdWarehouseDO> o2;
    private List<YdWarehousePercentDO> percent;

    public List<YdWarehousePercentDO> getPercent() {
        return percent;
    }

    public void setPercent(List<YdWarehousePercentDO> percent) {
        this.percent = percent;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getInOutFrameCount() {
        return inOutFrameCount;
    }

    public void setInOutFrameCount(double inOutFrameCount) {
        this.inOutFrameCount = inOutFrameCount;
    }

    public double getInOutPageCount() {
        return inOutPageCount;
    }

    public void setInOutPageCount(double inOutPageCount) {
        this.inOutPageCount = inOutPageCount;
    }

    public List<YdWarehouseDO> getO2() {
        return o2;
    }

    public void setO2(List<YdWarehouseDO> o2) {
        this.o2 = o2;
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<YdWarehouseLogDO> getO() {
        return o;
    }

    public void setO(List<YdWarehouseLogDO> o) {
        this.o = o;
    }
}
