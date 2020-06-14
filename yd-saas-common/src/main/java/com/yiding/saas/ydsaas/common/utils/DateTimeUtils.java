package com.yiding.saas.ydsaas.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间相关工具
 * @author Louis
 * @date Sep 23, 2018
 */
public class DateTimeUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
	public static final String DATE_FORMAT_ONE = "MM-dd";
	
	/**
	 * 获取当前标准格式化日期时间
	 * @param date
	 * @return
	 */
	public static String getDateTime() {
		return getDateTime(new Date());
	}
	
	/**
	 * 标准格式化日期时间
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {
		return (new SimpleDateFormat(DATE_FORMAT)).format(date);
	}
	
	/**
	 * 标准格式化日期时间
	 * @author wangcb
	 * @param date 当前时间
	 * @param format 自定义时间格式
	 * @return
	 */
	public static String getCustomDateTime(Date date,String format) {
		return (new SimpleDateFormat(format)).format(date);
	}
	
	/**
	 * 计算两个时间差
	 * @param startDate
	 * @return
	 */
	public static String getDatePoor(Date startDate) {
		Date nowDate = new Date();
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = nowDate.getTime() - startDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	
	/**
     * 秒转换为指定格式的日期
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(long second,String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }
    /**
     * 返回日时分秒
     * @param second
     * @return
     */
    public static String secondToTime(long second) {
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if (0 < days){
            return days + "天"+hours+"小时"+minutes+"分";
        }else {
            return hours+"小时"+minutes+"分";
        }
    }


    /**
     * 获取今年第一天
     *
     * @return
     */
    public static Date getCurrYearFirst() {
        LocalDateTime now = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, now.getYear());
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取今年最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        LocalDateTime now = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, now.getYear());
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }


    public static void main(String[] args) {
        //秒
        long second = 452255;
        //转换为日时分秒
        String days = secondToTime(second);
        System.out.println(days);
        //转换为所需日期格式
        String dateString = secondToDate(second,"yyyy-MM-dd hh:mm");

	}
}
