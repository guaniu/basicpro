package com.yiding.saas.ydsaas.web.util;

/**
 * redis key 前缀处理
 * tobacco_
 */
public class RedisKeyUtil {
    /**
     * 顶级前缀
     */
    private static final String topProfix = "tobacco_";
    /**
     * 地区
     */
    public static String district_key = topProfix + "district:";

    /**
     * 组织
     */
    public static String org_key = topProfix + "org:";
}
