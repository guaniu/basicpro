package com.yiding.saas.ydsaas.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * @author zhangjingrun
 *
 */
public class CheckDate {

	private static String SIMPLE_YMD = "yyyyMMdd";
	private static String SIMPLE_YMD_CHAR = "yyyy-MM-dd";
	private static String SIMPLE_YMD_HM_CHAR = "yyyy-MM-dd HH:mm";
	private static String SIMPLE_YMDHMS_CHAR = "yyyyMMddHHmmss";
	private static String SIMPLE_YMD_HMS_CHAR = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 检验是否当天日期
	 * @param str			日期
	 * @return				是否为当天日期
	 * @throws Exception
	 */
	public static boolean isToday(String str) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c1 = Calendar.getInstance();              
		c1.setTime(date);                                 
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH)+1;
		int day1 = c1.get(Calendar.DAY_OF_MONTH);     
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(new Date());
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH)+1;
		int day2 = c2.get(Calendar.DAY_OF_MONTH);   
		if(year1 == year2 && month1 == month2 && day1 == day2){
			return true;
		}
		return false;   
	}
	
	/**
	 * 生成当天年月日日期
	 * @return	返回举例：20180803
	 */
	public static String generateYMD() {
		String currday = "";
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(new Date());
		currday += c2.get(Calendar.YEAR);
		if ((c2.get(Calendar.MONTH)+1) < 10) {
			currday += "0" + (c2.get(Calendar.MONTH)+1);
		}else {
			currday += (c2.get(Calendar.MONTH)+1);
		}
		if (c2.get(Calendar.DATE) < 10) {
			currday += "0" + c2.get(Calendar.DATE);
		}else {
			currday += c2.get(Calendar.DATE);
		}
		return currday;
	}
	
	/**
	 * 返回日期字型串
	 */
	public static String getCharDate(Date date) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		result = format.format(date);
		return result;
	}
	
	/**
	 * 返回日期字型串
	 */
	public static String getDate(Date date) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_HM_CHAR);
		result = format.format(date);
		return result;
	}
	
	/**
	 * 返回日期型
	 * @param args
	 */
	public static Date formatDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_HM_CHAR);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回年-月-日
	 * @param args
	 */
	public static Date formatDateYMD(String date) {
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回 年月日时分秒
	 * @param args
	 */
	public static String formatYMDHMS(String date) {
		try {
			date = date.replace("T", "");
			date = date.replace("Z", "");
			SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMDHMS_CHAR);
			SimpleDateFormat format1 = new SimpleDateFormat(SIMPLE_YMD_HMS_CHAR);
			return format1.format(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
         * 返回 年月日时分
         * @param args
         */
        public static String formatYMDHM(String date) {
                try {
                        date = date.replace("T", "");
                        date = date.replace("Z", "");
                        SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMDHMS_CHAR);
                        SimpleDateFormat format1 = new SimpleDateFormat(SIMPLE_YMD_HM_CHAR);
                        return format1.format(format.parse(date));
                } catch (ParseException e) {
                        e.printStackTrace();
                        return "";
                }
        }
	
	public static void main(String[] args) {
		
		try {
			//System.out.println(isToday("20180804"));
			//System.out.println(generateYMD());
			//System.out.println(getCharDate(new Date()));
			
//			Calendar c2 = Calendar.getInstance(); 
//			c2.setTime(new Date());
//			int year2 = c2.get(Calendar.YEAR);
//			int month2 = c2.get(Calendar.MONTH)+1;
//			int day2 = c2.get(Calendar.DAY_OF_MONTH);
//			System.out.println("" + year2 + month2 + day2);
			
			System.out.println(formatYMDHMS("20181009T095930Z"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	   * 得到几天前的时间
	   * @param d
	   * @param day
	   * @return
	*/
	public static Date getDateBefore(String d,int day){
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		Calendar now =Calendar.getInstance();
		Date date;
		try {
			date = format.parse(d);
			now.setTime(date);
			now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now.getTime();
	}
	  
	/**
	   * 得到几天后的时间
	 * @param d
	 * @param day
	 * @return
	*/
	public static Date getDateAfter(String d,int day){
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		Calendar now =Calendar.getInstance();
		Date date;
		try {
			date = format.parse(d);
			now.setTime(date);
			now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now.getTime();
	}
	
	/**
	   * 得到几天前的时间
	   * @param d
	   * @param day
	   * @return
	*/
	public static String getDateBeforeString(Date d,int day){
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		Calendar now =Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		return format.format(now.getTime());
	}
	  
	/**
	   * 得到几天后的时间
	 * @param d
	 * @param day
	 * @return
	*/
	public static String getDateAfterString(Date d,int day){
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		Calendar now =Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
		return format.format(now.getTime());
	}
	
	/**
	 * 得到当天日期字符型
	 */
	public static String getDateTodayString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(SIMPLE_YMD_CHAR);
		return format.format(date);
	}
	
	/**
	 * 获取该天的毫秒数
	 */
	public static long getDateMillisecond(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE,c.get(Calendar.DATE)-days);
		Date beforeDate = c.getTime();
		return beforeDate.getTime();
	}
}
