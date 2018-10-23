package com.rensu.education.hctms.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class DateUtil {
	
	public static String format(Date date, String pattern) {
		SimpleDateFormat DATEFORMAT = new SimpleDateFormat(pattern);
		String retv = DATEFORMAT.format(date);
		return retv;
	}
	
	/***
	 * 得到传进去的日期是星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date){ 
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(date);
		return week;
	}
	
	/*根据传入格式获取当前日期时间*/
	public static String getCurrDateTime(String pattern) {
		return format(new Date(), pattern);
	}
	
	/*获取当前日期时间*/
	public static String getCurrDateTime() {
		return format(new Date(), "yyyyMMddHHmmss");
	}

	/*获取当前日期*/
	public static String getCurrDate() {
		return format(new Date(), "yyyyMMdd");
	}
	
	/*获取当前日期*/
	public static String getCurrDate_Format() {
		return format(new Date(), "yyyy-MM-dd");
	}

	/*获取当前时间（时分秒）*/
	public static String getCurrTime() {
		return format(new Date(), "HHmmss");
	}
	
	/*获取传入日期增加或减少天数后的日期时间*/
	public static String addDate(String date, int days, String pattern) {
		try {
			Date ds = parse(date, "yyyyMMddHHmmss");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(ds);
			calendar.add(6, days);
			return format(calendar.getTime(), pattern);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Date parse(String dateStr, String pattern) {
		Date ret = null;
		try {
			if ((dateStr != null) && (dateStr.trim().length() > 0)) {
				SimpleDateFormat DATEFORMAT = new SimpleDateFormat(pattern);
				ret = DATEFORMAT.parse(dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static Timestamp str2Timestamp(String str,String pattern) {
	  if(null != str && !"".equals(str)){
	    Date date = parse(str, pattern);
	    return new Timestamp(date.getTime());
	  }
	  return null;
	}
	
	public static long betweenTwoDayslong(String day1, String day2) {
		long result = 0L;
		try {
			Date date1 = parse(day1,"yyyyMMddHHmmss");
			Date date2 = parse(day2,"yyyyMMddHHmmss");
			result = date1.getTime() - date2.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static long getbetweenTwoDayslong(String day1, String day2) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long result = 0L;
		try {
			Date date1 = dfs.parse(day1);
			Date date2 = dfs.parse(day2);
			result = date1.getTime() - date2.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String betweenTwoDateTimeStr(String datetime1, String datetime2){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(datetime1);
			date2 = df.parse(datetime2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = date2.getTime() - date1.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long _hour = hour+(day*24);
		String rtnStr = _hour+"小时"+min+"分"+s+"秒";
		return rtnStr;
	}
	
	/***
	 * 两个时间相差的毫秒数
	 * @param datetime1
	 * @param datetime2
	 * @return
	 * @author yuanb
	 * @date 2016年6月29日
	 */
	public static String betweenTwoDateTimeMilliStr(String datetime1, String datetime2){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = df.parse(datetime1);
			date2 = df.parse(datetime2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long l = date2.getTime() - date1.getTime();
		return String.valueOf(l);
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
	
	//20150908 改为 2015-09-08
	public static String getFormatDateString(String date){
		String newDate = "";
		newDate = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
		return newDate;
	}
	
	/***
	 * 根据两个日期，得到距离开始时间过去了多少周
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getWeekNumByTwoDate(String start,String end){
		String res = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            long from = df.parse(start).getTime();
            long to = df.parse(end).getTime();
            if(from > to){
            	res = "0";
            }else if(from == to){
            	res = "1";
            }else{
            	res = (to-from)/(1000*3600*24*7)+"";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return res;
	}
	
	/**
	 * 给定任一日期，返回当前日期所在周的周一的日期。
	 * 在Calendar API中，一周是从周日开始算的 。
	 * 周日在DAY_OF_WEEK中返回1，周一返回2，依次类推。
	 * @param cal
	 * @return
	 * @date 2016年5月26日
	 * @autor chen xiaoxiao
	 */
	public static Calendar getFirstDayOfWeek(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int addDays = 0 - ((dayOfWeek + 7 - 2) % 7);
		cal.add(Calendar.DAY_OF_MONTH, addDays);
		return cal;
	}
	
	/**
	 * 参见方法：getFirstDayOfWeek(Calendar cal)
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @date 2016年5月26日
	 * @autor chen xiaoxiao
	 */
	public static Calendar getFirstDayOfWeek(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return getFirstDayOfWeek(cal);
	}
	
	/**
	 * dateStr 必须满足yyyy-mm-dd格式。
	 * @param date
	 * @return
	 * @date 2016年5月26日
	 * @autor chen xiaoxiao
	 */
	public static Calendar getFirstDayOfWeek(String dateStr) {
		if (StringUtil.isEmpty(dateStr)) {
			return null;
		}
		String[] dateArr = dateStr.split(Consts.SPLIT_FLAG_SHORT_STRING);
		if (dateArr.length < 3) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]) - 1, Integer.parseInt(dateArr[2]));
		
		return getFirstDayOfWeek(cal);
	}
	
	/**
	 * 根据给定的周一的日期，将这一整周的日期返回。yyyyMMdd格式。
	 * @param cal
	 * @return
	 * @date 2016年5月26日
	 * @autor chen xiaoxiao
	 */
	public static String[] getDates(Calendar cal,SimpleDateFormat sdf) {
		String[] dateArr = new String[7];
		Calendar calNew = (Calendar)cal.clone();
		for (int i = 0; i < 7; i++) {
			dateArr[i] = sdf.format(calNew.getTime());
			calNew.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dateArr;
	}
	
	 /** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    }
	
    public static String getDateByWeek(String day,int week){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.add(Calendar.WEEK_OF_YEAR, week);
		Date days = calendar.getTime();
		return sdf.format(days);
	}
	
	public static String getDateByMonth(String day,int month){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH, month);
		Date days = calendar.getTime();
		return sdf.format(days);
	}
	
	
}
