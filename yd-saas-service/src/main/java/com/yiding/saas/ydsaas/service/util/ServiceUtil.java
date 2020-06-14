package com.yiding.saas.ydsaas.service.util;

import com.yiding.saas.ydsaas.common.core.ColumnFilter;
import com.yiding.saas.ydsaas.common.core.PageRequest;

import java.util.Map;

public class ServiceUtil {
    /**
     * 获取过滤字段的值
     * @param filterName
     * @return
     */
    public static Object getColumnFilterValue(PageRequest pageRequest, String filterName) {
        Object value = null;
        Map params =  (Map) pageRequest.getColumnFilters().get("params");
        if(params != null) {
            value = params.get(filterName);
        }
        return value;
    }
}
