package com.yiding.saas.ydsaas.dao;

import com.yiding.saas.ydsaas.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public interface YdWarehouseMapper {

    List<YdWarehouseLogDO> findPageList(YdWarehousePageDO ydWarehouseDO);

    int getCounts(YdWarehousePageDO ydWarehouseDO);

    YdWarehouseLogDO warehouseDetails(@Param("logId") Integer logId);

    List<YdProductInfoDO> getProductInfos(@Param("logId")Integer logId);

    void saveLogInfo(YdWarehouseLogDO ydWarehouseLogDO);

    void saveProductInfo(YdWarehouseLogDO ydWarehouseLogDO);

    void saveWarehouse(YdWarehouseLogDO ydWarehouseLogDO);

    YdWarehouseLogDO getWarehouseInfo(@Param("tobaccoStation") String tobaccoStation,@Param("tobaccoId") int tobaccoId,@Param("repertoryId") int repertoryId);

    List<YdWarehouseDO> findPageWarehouseList(YdWarehousePageDO ydWarehousePageDO);

    List<YdWarehouseLogDO> getRepertory(YdWarehouseLogDO ydWarehouseLogDO);

    List getSonIdLists(@Param("parentId") int parentId);

    List getDistrictId(List list1);

    int getCounts2(YdWarehousePageDO ydWarehousePageDO);

    List<String> getDistictName(@Param("ids") String[] ids);

    List getRepertoryNames(YdWarehouseLogDO ydWarehouseLogDO);

    List<YdWarehousePercentDO> findWarehouseList(YdWarehousePageDO ydWarehousePageDO);


    List<YdWarehouseLogDO> getListByRfid(@Param("rfids") List<String> rfid);

    List<YdWarehouseDO> findPageWarehouseList2(YdWarehousePageDO ydWarehousePageDO);

    List<YdWarehouseLogDO> findList(@Param("transportId") Integer transportId);

    List<YdTobacco> getTobaccoLevels();

    List<YdWarehouseDO> findTobaccoWarehouseList(YdWarehousePageDO ydWarehousePageDO);

    List<YdWarehouseLogDO> findTodayList(YdWarehouseDO ydWarehouseDO);

    List<YdWarehouseLogDO> findByRfidList(YdWarehousePageDO ydWarehousePageDO);

    List<YdWarehouseLogDO> findPageByRfidList(YdWarehousePageDO ydWarehousePageDO);

    int getCounts3(YdWarehousePageDO ydWarehousePageDO);

    List<YdWarehouseLogDO> findByRfids(YdWarehouseLogDO ydWarehouseLogDO);

    YdWarehouseLogDO findLogInfoById(int logId);

    void updateWarehouse(YdWarehouseLogDO ydWarehouseLogDO);

    void updateLogInfo(YdWarehouseLogDO ydWarehouseLogDO);

    void updateProductInfo(YdWarehouseLogDO ydWarehouseLogDO);
}
