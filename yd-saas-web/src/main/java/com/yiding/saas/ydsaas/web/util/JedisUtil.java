package com.yiding.saas.ydsaas.web.util;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;


/***
 * Jedis工具类
 */
public class JedisUtil {


    /**
     * 设置Strring类型的值
     *
     * @param key     tobacco_*
     * @param value
     * @param seconds 过期时间 s
     * @return
     */
    public static boolean setKey(String key, String value, int seconds) {

        Jedis jedis = null;

        try {
            jedis = JedisPoolUtil.getJedis();
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return true;

    }


    /***
     * 获取String类型的值
     * @param key
     * @return
     */
    public static String getValue(String key) {

        Jedis jedis = null;
        String value = "";
        try {
            jedis = JedisPoolUtil.getJedis();
            value = jedis.get(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return value;
    }

    /***
     * 根据key值删除数据,可批量删除
     * @param keys
     * @return
     */
    public static boolean delKey(String... keys) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            jedis.del(keys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return false;
    }


    /**
     * 判断key值是否存在
     *
     * @param key
     * @return
     */
    public static boolean isExists(String key) {
        Jedis jedis = null;
        boolean isExists = false;
        try {
            jedis = JedisPoolUtil.getJedis();
            isExists = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return isExists;
    }


    /**
     * 根据前缀获取key信息
     *
     * @param prefix
     * @return
     */
    public static Set<String> getKeys(String prefix) {

        Jedis jedis = null;
        Set<String> keys = new HashSet<>();
        try {
            jedis = JedisPoolUtil.getJedis();
            //禁止keys *
            if (StringUtils.isBlank(prefix) || prefix.equals("*")) {
                return keys;
            }
            keys = jedis.keys(prefix + "*");
            return keys;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.returnJedis(jedis);
        }
        return keys;
    }


}
