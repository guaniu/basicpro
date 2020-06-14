package com.yiding.saas.ydsaas.service.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 94307 on 2019/3/29.
 */
public class DateUtils {

    /**
     * 获取当前日期
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static Date getCurrentDate(){
        return new Date();
    }

    /**
     * 天数差
     */
    public static int daysDiffent(String createTime) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date fromDate1 = simpleFormat.parse(createTime);
        String toDate= simpleFormat.format(System.currentTimeMillis());
        Date toDate1 = simpleFormat.parse(toDate);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        System.out.println("两个时间之间的天数差为：" + days);
        return days;
    }
    /**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
    /**
	 * 计算两个日期相差的月份
	 * @author wangcb
	 * @param start
	 * @param end
	 * @return
	 */
	public int getMonthDist(Date start,Date end) {
		Double days = DateUtils.getDistanceOfTwoDate(start,end);
		int day = days.intValue();
		return day/30;
	}
    /**
     * 小时差
     */
    public static int hoursDiffent(String createTime) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date fromDate2 = simpleFormat.parse(createTime);
        String toDate= simpleFormat.format(System.currentTimeMillis());
        Date toDate2 = simpleFormat.parse(toDate);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
        System.out.println("两个时间之间的小时差为：" + hours);
        return hours;
    }

    /**
     * 分钟差
     */
    public static int minsDiffent(String createTime) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date fromDate3 = simpleFormat.parse(createTime);
        String toDate= simpleFormat.format(System.currentTimeMillis());
        Date toDate3 = simpleFormat.parse(toDate);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        System.out.println("两个时间之间的分钟差为：" + minutes);
        return minutes;
    }

    /**
     * 时间差 eg: 34天2小时13分种
     * 最大差值单位:day  最小差值单位:min
     */
    public static String timeDiffent(String createTime) throws ParseException {
        int min=minsDiffent(createTime);
       if (min<60){
           return String.valueOf(min)+"分钟";
       }else if (min>60 && min<1440){
           int mins=min%60;
           int hours=min/60;
           return String.valueOf(hours)+"小时"+String.valueOf(mins)+"分钟";
       }
       int days=min/1440;
       int dayhelp=min%1440;
       int hours=dayhelp/60;
       int mins=dayhelp%60;
       return days+"天"+hours+"小时"+mins+"分钟";
    }

    /**
     * 将Timestamp转换成String
     * 用于数据库中字段类型为datetime
     * @param timestamp
     * @return
     */
    public static String time2String(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        String dateStr = date2String(date);
        return dateStr;
    }
    /**
     * 将Date转换成String 精确到秒
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 将Date转换成String 精确到天
     * @param date
     * @return
     */
    public static String date3String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static void main(String[] args){
        Date date=new Date("Tue Apr 02 06:56:10 CST 2019");
        System.out.println(date2String(date));
    }
    
    /**
     * 	字符串转日期类型
     */
    public static Date strToDate(String str) {
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	//yyyy-MM-dd HH:mm:ss
    	 Date date = new Date();
    	try {
            date=simpleDateFormat.parse(str);
        } catch(ParseException px) {
            px.printStackTrace();
        }
    	return date;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
