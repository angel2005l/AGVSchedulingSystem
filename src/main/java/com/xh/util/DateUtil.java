package com.xh.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtil {

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	// 基本类型
	public static final String YM = "yyyy-MM";
	public static final String YMD = "yyyy-MM-dd";
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHM = "yyyy-MM-dd HH:mm";

	// 业务格式 （连续）
	private static final String JOINT_YMD = "yyyyMMdd";
	private static final String JOINT_YMDHM = "yyyyMMddHHmm";
	private static final String JOINT_YMDHMS = "yyyyMMddHHmmss";
	private static final String JOINT_YMDHMSS = "yyyyMMddHHmmssSSS";

	// 格式转换工具
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat();

	/**
	 * 
	 * @Title: isValidDate
	 * @Description: 校验 日期字符串是否正确
	 * @param: @param
	 *             date
	 * @param: @param
	 *             pattern
	 * @param: @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static boolean isValidDate(String date, String pattern) {
		if (StrUtil.isBlank(date) || StrUtil.isBlank(pattern)) {
			return false;
		}
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			// 设置lenient为false.
			// 严格的解析,输入必须匹配这个对象的格式。
			format.setLenient(false);
			format.parse(date);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 
	 * @Title: curDateYM
	 * @Description: 获得当前时间（yyyy-MM）
	 * @param: @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYM() {
		dateFormat.applyPattern(YM);
		return dateFormat.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Title: curTimeYMD
	 * @Description: 获得当前日期 （yyyy-MM-dd）
	 * @param: @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYMD() {
		dateFormat.applyPattern(YMD);
		return dateFormat.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Title: curTime
	 * @Description: 获得当前时间（yyyy-MM-dd HH:mm:ss）
	 * @param: @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYMDHMS() {
		dateFormat.applyPattern(YMDHMS);
		return dateFormat.format(System.currentTimeMillis());
	}

	/*
	 * 业务获得ID的日期识别码
	 */

	/**
	 * 
	 * @Title: curDateYMDForservice
	 * @Description: 获得当前业务ID识别码 （yyyyMMdd）
	 * @param: @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYMDForservice() {
		dateFormat.applyPattern(JOINT_YMD);
		return dateFormat.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Title: curDateYMDHMForService
	 * @Description: 获得当前业务ID识别码 （yyyyMMddHHmm）
	 * @param: @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYMDHMForService() {
		dateFormat.applyPattern(JOINT_YMDHM);
		return dateFormat.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Title: curDateYMDHMSForService
	 * @Description: 获得当前业务ID识别码（yyyyMMddHHmmss）
	 * @author 黄官易
	 * @return
	 * @return String
	 * @date 2018年6月20日
	 * @version 1.0
	 */
	public static String curDateYMDHMSForService() {
		dateFormat.applyPattern(JOINT_YMDHMS);
		return dateFormat.format(System.currentTimeMillis());
	}

	/**
	 * 
	 * @Title: curDateYMDHMSSForService
	 * @Description: 获得当前业务ID识别码 （yyyyMMddHHmmssSSS）
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String curDateYMDHMSSForService() {
		dateFormat.applyPattern(JOINT_YMDHMSS);
		return dateFormat.format(System.currentTimeMillis());
	}
	/*
	 * 字符串转换Date
	 */

	/**
	 * 
	 * @Title: curDateByStr
	 * @Description: 获得当前时间 （yyyy-MM-dd HH:mm:ss）
	 * @param: @return
	 * @author: MR.H
	 * @return: Date
	 *
	 */
	public static Date curDateByStr() {
		dateFormat.applyPattern(YMDHMS);
		try {
			return dateFormat.parse(curDateYMDHMS());
		} catch (ParseException e) {
			log.error("时间工具类【curDateByStr】方法异常,异常原因:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @Title: curDateByStr
	 * @Description: 根据日期格式,转换传入的日期字符串
	 * @param: @param
	 *             date
	 * @param: @param
	 *             pattern
	 * @param: @return
	 * @author: MR.H
	 * @return: Date
	 *
	 */
	public static Date curDate(String date, String pattern) {
		pattern = StrUtil.isBlank(pattern) ? YMDHMS : pattern;
		if (!isValidDate(date, pattern)) {
			return null;
		}
		dateFormat.applyPattern(pattern);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			log.error("时间工具类【curDate】[string,string]方法异常,异常原因:" + e.getMessage());
			return null;
		}
	}

	public static String curDateByStr(String date, String pattern) {
		pattern = StrUtil.isBlank(pattern) ? YMDHMS : pattern;
		if (!isValidDate(date, pattern)) {
			return null;
		}
		try {
			dateFormat.applyPattern(pattern);
			return dateFormat.format(dateFormat.parse(date));
		} catch (ParseException e) {
			log.error("时间工具类【curDateByStr】[string,string]方法异常,异常原因:" + e.getMessage());
			return null;
		}
	}

	/*
	 * 拓展方法
	 */

	/**
	 * 
	 * @Title: addDay
	 * @Description: 几天后
	 * @param day
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static Date addDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate(curDateYMD(), DateUtil.YMD));
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	/**
	 * 
	 * @Title: addDay
	 * @Description: 根据特定年月日增加响应天数
	 * @author 黄官易
	 * @param startDate
	 * @param day
	 * @return
	 * @return String
	 * @date 2018年7月11日
	 * @version 1.0
	 */
	public static String addDay(String startDate, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate(startDate, DateUtil.YMD));
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 
	 * @Title: curTimestampByStr
	 * @Description: 根据日期时间字符串转化为日期时间对象
	 * @param date
	 * @return
	 * @return Timestamp
	 * @author 黄官易
	 * @date 2018.04.19
	 */
	public static Timestamp curTimestampByStr(String date) {
		if (StrUtil.isBlank(date)) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate(date, YMDHMS));
		calendar.getTimeInMillis();
		return new Timestamp(calendar.getTimeInMillis());
	}

	/**
	 * 
	 * @Title: getDay
	 * @Description: 获得 输入日期时间与系统时间之间相差多少小时 （0.5小时 向上取整）
	 * @param date
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static String getHour(String date) {
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(curDate(date, YMDHMS));
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(curDate(curDateYMDHMS(), YMDHMS));
		BigDecimal timeNum = new BigDecimal((endTime.getTimeInMillis() - startTime.getTimeInMillis()) + "");
		BigDecimal result = timeNum
				.divide(new BigDecimal("1000").multiply(new BigDecimal("60")).multiply(new BigDecimal("30")), 0,
						BigDecimal.ROUND_UP)
				.multiply(new BigDecimal("0.5"));

		return result.toString();
	}

	// 判断选择的日期是否是本周
	public static boolean isThisWeek(long time) {
		Calendar calendar = Calendar.getInstance();
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.setTime(new Date(time));
		int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		if (paramWeek == currentWeek) {
			return true;
		}
		return false;
	}

	// 判断选择的日期是否是今天
	public static boolean isToday(long time) {
		return isThisTime(time, "yyyy-MM-dd");
	}

	// 判断选择的日期是否是本月
	public static boolean isThisMonth(long time) {
		return isThisTime(time, "yyyy-MM");
	}

	private static boolean isThisTime(long time, String pattern) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);// 参数时间
		String now = sdf.format(new Date());// 当前时间
		if (param.equals(now)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @Title: getWeekNum
	 * @Description: 今天是一周的第几天
	 * @return
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public static int getWeekNum() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;// 适应我国国情
	}

	/**
	 * 
	 * @Title: getMonthNum
	 * @Description: 今天是一个月的第几天
	 * @return
	 * @author: MR.H
	 * @return: int
	 *
	 */
	public static int getMonthNum() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	// /**
	// *
	// * @Title: addMonth
	// * @Description: 几月后
	// * @param: @return
	// * @author: MR.H
	// * @return: String
	// *
	// */
	// public static String addMonth() {
	//
	// return "";
	// }
	//
	// /**
	// *
	// * @Title: addYear
	// * @Description: 几年后
	// * @param: @return
	// * @author: MR.H
	// * @return: String
	// *
	// */
	// public static String addYear() {
	// return "";
	// }

}
