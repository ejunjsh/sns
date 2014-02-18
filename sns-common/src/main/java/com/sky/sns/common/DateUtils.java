package com.sky.sns.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
     public static String format(Date date,String format)
     {
    	 if(date!=null)
    	 {
	    	 SimpleDateFormat sdf = new SimpleDateFormat(format);
	    	 String dateStr = sdf.format(date);
	    	 return dateStr;
    	 }
    	 else
    		 return null;
    	 
     }
     
     /**
 	 * 时间间隔计算
 	 * 
 	 */
 	public static String getDaysBeforeNow(Date date) {
 		long sysTime = Long.parseLong(DateUtils.format(new Date(),"yyyyMMddHHmmss"));
 		long ymdhms = Long.parseLong(DateUtils.format(date,"yyyyMMddHHmmss"));
 		String strHour = "小时前";
 		String strMinute = "分钟前";
 		String strSecond="秒前";
 		try {
 			if (ymdhms == 0) {
 				return "";
 			}
 			long between = (sysTime / 1000000L) - (ymdhms / 1000000L);
 			if (between >0) {
 				return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
 			}
 			
 			between = (sysTime / 10000) - (ymdhms / 10000);
 			if (between > 0) {
 				if(between<3)
 				return between + strHour;
 				else
 				return DateUtils.format(date, "今天 HH:mm:ss");
 			}
 			between = (sysTime / 100) - (ymdhms / 100);
 			if (between > 0) {
 				return between + strMinute;
 			}
 			between = (sysTime) - (ymdhms);
 			if (between > 0) {
 				return between + strSecond;
 			}
 			return "1" + strSecond;
 		} catch (Exception e) {
 			return "";
 		}
 	}
}