package com.yiding.saas.ydsaas.service;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.model.YdPageDO;
import com.yiding.saas.ydsaas.model.YdWarehouseDO;
import com.yiding.saas.ydsaas.model.YdWarehousePageDO;
import com.yiding.saas.ydsaas.model.YdWarehouseLogDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author BruceLee
 * @Date 2020/5/25
 */
public interface YdWarehouseService {
    YdPageDO findPageList(YdWarehousePageDO ydWarehouseDO);

    YdWarehouseLogDO warehouseDetails(Integer logId);

    String saveWarehouse(YdWarehouseLogDO ydWarehouseLogDO);

    YdPageDO  findPageWarehouseList(YdWarehousePageDO ydWarehousePageDO);

    Map  getRepertory(YdWarehouseLogDO ydWarehouseLogDO);
    
    List<YdWarehouseLogDO> getListByRfid(@Param("rfids") List<String> rfids);
    
    List<YdWarehouseLogDO> findList(Integer transportId);

    Map findByRfidList(YdWarehousePageDO ydWarehousePageDO);

    void findWarehouseScreenData(YdWarehousePageDO ydWarehousePageDO,JSONObject json);

    String saveRfids(List list);

    Map updateWarehouse(YdWarehouseLogDO ydWarehouseLogDO);
}
