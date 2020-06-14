package com.yiding.saas.ydsaas.service.util;

import com.yiding.saas.ydsaas.model.YdPageDO;
import com.yiding.saas.ydsaas.model.YdWarehouseDO;
import com.yiding.saas.ydsaas.model.YdWarehouseLogDO;
import com.yiding.saas.ydsaas.model.YdWarehousePercentDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author BruceLee
 * @Date 2020/5/27
 */
public class PageUtils {

    public static YdPageDO getLogPage(List<YdWarehouseLogDO> list, Integer pageNums, Integer pageSize, Integer totalSize) {
        YdPageDO ydPageDO = new YdPageDO();

        int totalPage = 1;
        if (totalSize < pageSize) {
            ydPageDO.setTotalPage(totalPage);
        }else if (totalSize%pageSize!=0&&totalSize>pageSize){
            ydPageDO.setTotalPage(totalSize/pageSize+1);
        }else {
            ydPageDO.setTotalPage(totalSize/pageSize);
        }
        ydPageDO.setPageNum(pageNums);
        ydPageDO.setTotalSize(totalSize);
        ydPageDO.setPageSize(pageSize);
        ydPageDO.setO(list);
       return ydPageDO;
    }

    public static YdPageDO getRepertoryPage(List<YdWarehouseDO> list, List<YdWarehousePercentDO> list6, Integer pageNums, Integer pageSize, Integer totalSize) {
        YdPageDO ydPageDO = new YdPageDO();

        int totalPage = 1;
        if (totalSize < pageSize) {
            ydPageDO.setTotalPage(totalPage);
        }else if (totalSize%pageSize!=0&&totalSize>pageSize){
            ydPageDO.setTotalPage(totalSize/pageSize+1);
        }else {
            ydPageDO.setTotalPage(totalSize/pageSize);
        }
        ydPageDO.setPageNum(pageNums);
        ydPageDO.setTotalSize(totalSize);
        ydPageDO.setPageSize(pageSize);
        ydPageDO.setO2(list);
        ydPageDO.setPercent(list6);
        return ydPageDO;
    }
    public static Map getRepertoryPage2(List<YdWarehouseLogDO> list1,Map map, Integer pageNums, Integer pageSize, Integer totalSize) {
        int totalPage = 1;
        if (pageNums!=0&&pageSize!=0) {
            if (totalSize < pageSize) {
                map.put("totalPage", totalPage);
            } else if (totalSize % pageSize != 0 && totalSize > pageSize) {
                map.put("totalPage", totalSize / pageSize + 1);
            } else {
                map.put("totalPage", totalSize / pageSize);
            }
        }
        map.put("pageNum",pageNums);
        map.put("pageSize",pageSize);
        map.put("totalSize",totalSize);
        map.put("logList",list1);
        return map;
    }
}
