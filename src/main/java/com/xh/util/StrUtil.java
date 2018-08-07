package com.xh.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @project 常用的字符串工具类
 * @date 2018.03.19
 * @author 黄官易
 * @version DEMO 0.0.1
 * @introduce
 */
public final class StrUtil {
	/**
	 * 
	 * @Title: isBlank   
	 * @Description: 校验字符串是否为空 当字符串为空时返回true
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean isBlank(String str) {
		return !notBlank(str);
	}

	/**
	 * 
	 * @Title: notBlank   
	 * @Description: 校验字符串是否为空 当字符串不为空则返回true
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean notBlank(String str) {
		return null != str && !"".equals(str.trim());
	}

	/**
	 * 
	 * @Title: isPositiveInteger   
	 * @Description: 校验字符串是否是一个正整数
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean isPositiveInteger(String str) {
		return str.matches("^[1-9]\\d*$");
	}

	/**
	 * 
	 * @Title: iPositiveNum   
	 * @Description: 是否是一个正数
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean iPositiveNum(String str) {
		return str.matches("^[1-9]\\d*\\.\\d*\\|0\\.\\d*[1-9]\\d*$");
	}

	/**
	 * 
	 * @Title: replaceBlank   
	 * @Description: 将字符串中的回车换行符 换成想要的符号 
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String replaceBlank(String str, String symbol) {
		Pattern p = Pattern.compile("\\n");
		Matcher m = p.matcher(str);
		return m.replaceAll(isBlank(symbol) ? "" : symbol);
	}
	/**
	 * 
	 * @Title: replaceAllStr   
	 * @Description: 将字符串中的特定字符替换
	 * @param str
	 * @param regex
	 * @param symbol
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String replaceAllStr(String str,String regex,String symbol){
		return str.replaceAll(regex, symbol);
	}
	
	/**
	 * 
	 * @Title: cutStringForCenter   
	 * @Description: 切割字符串（中间）
	 * @param str
	 * @param beginIndex
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String cutStringForCenter(String str, int beginIndex, int size) {
		String result = null;
		int strLength = str.length();
		if (strLength < size) {
			result = strAddSpace(str, size);
		} else {
			int diffSize = strLength - beginIndex;
			if (diffSize < 0) {
				result = "";
			} else if (diffSize <= size) {
				result = strAddSpace(str.substring(beginIndex), size);
			} else {
				result = str.substring(beginIndex, beginIndex + size);
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: strAddSpace   
	 * @Description: 当切割长多大于字符串长度时 在尾部自增空格
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	private static final String strAddSpace(String str, int size) {
		String result = str;
		for (int i = 0; i < size - str.length(); i++) {
			result += " ";
		}
		return result;
	}

	/**
	 * 
	 * @Title: strAddLeftZero   
	 * @Description: 在截取字段前补零
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String strAddLeftZero(String str, int size) {
		String zeroStr = "";
		for (int i = 0; i < size - str.length(); i++) {
			zeroStr += "0";
		}
		return zeroStr + str;
	}

	/**
	 * 
	 * @Title: cutStringForLeftFixS   
	 * @Description: 左边 定长
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: String      
	 *
	 */
	public static final String cutStringForLeftFixS(String str, int size) {
		String result = null;
		if (isShort(str, size)) {
			result = str;
		} else {
			result = str.substring(0, size);
		}
		return result;
	}

	/**
	 * 
	 * @Title: cutStringForLeft   
	 * @Description: 左边 非定长
	 * @param str
	 * @param fixedHeight
	 * @return
	 * @author: MR.H
	 * @return: String      
	 *
	 */
	public static final String cutStringForLeft(String str, int fixedHeight) {
		String result = null;
		if (isShort(str, fixedHeight)) {
			result = "";
		} else {
			result = str.substring(0, str.length() - fixedHeight);
		}
		return result;
	}

	/**
	 * 
	 * @Title: cutStringLeftRtnInteger   
	 * @Description: 左边截取字符串 返回一个Integer  默认值为null
	 * @param str
	 * @param size
	 * @param isFixed
	 * @return
	 * @author: MR.H
	 * @return: Integer      
	 *
	 */
	public static final Integer cutStringLeftRtnInteger(String str, int size, boolean isFixed) {
		return Integer.getInteger(isFixed ? cutStringForLeftFixS(str, size) : cutStringForLeft(str, size), null);
	}

	/**
	 * 
	 * @Title: cutStringRightRtnInteger   
	 * @Description: 左边截取字符串 返回一个Integer  默认值为null
	 * @param str
	 * @param size
	 * @param isFixed
	 * @return
	 * @author: MR.H
	 * @return: Integer
	 *
	 */
	public static final Integer cutStringRightRtnInteger(String str, int size, boolean isFixed) {
		return Integer.getInteger(isFixed ? cutStringForRightFixS(str, size) : cutStringForRight(str, size), null);
	}

	/**
	 * 
	 * @Title: cutStringForRightFixS   
	 * @Description: 右边 定长
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: String      
	 *
	 */
	public static final String cutStringForRightFixS(String str, int size) {
		String result = null;
		if (isShort(str, size)) {
			result = str;
		} else {
			int strLenght = str.length();
			result = str.substring(strLenght - size, strLenght);
		}
		return result;
	}

	/**
	 * 
	 * @Title: cutStringForRight   
	 * @Description: 右边 非定长
	 * @param str
	 * @param fixedHeight
	 * @return
	 * @author: MR.H
	 * @return: String      
	 *
	 */
	public static final String cutStringForRight(String str, int fixedHeight) {
		String result = null;
		if (isShort(str, fixedHeight)) {
			result = "";
		} else {
			result = str.substring(fixedHeight);
		}
		return result;
	}

	/**
	 * 
	 * @Title: isShort   
	 * @Description: 判断字符串长度  （小于或等于）
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: boolean      
	 *
	 */
	public static final boolean isShort(String str, int size) {
		return isBlank(str) ? true : size >= str.length();
	}

	/**
	 * 
	 * @Title: notshort   
	 * @Description: 判断字符串长度  （大于）
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: boolean      
	 *
	 */
	public static final boolean notshort(String str, int size) {
		return isBlank(str) ? false : !isBlank(str);
	}

	/**
	 * 
	 * @Title: strToLower   
	 * @Description: 将字符串变成小写 
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String strToLower(String str) {
		return str.toLowerCase();
	}

	/**
	 * 
	 * @Title: strToUp   
	 * @Description: 将字符串变成大写
	 * @param str
	 * @return
	 * @author: MR.H
	 * @return: String
	 *
	 */
	public static final String strToUpper(String str) {
		return str.toUpperCase();
	}
}
