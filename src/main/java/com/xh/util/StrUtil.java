package com.xh.util;

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
	public static final boolean isPositiveNum(String str) {
		return str.matches("^[1-9]\\d*\\.\\d*\\|0\\.\\d*[1-9]\\d*$");
	}
	/**
	 * 
	 * @Title: isNaturalNumber  
	 * @Description: 是否是一个自然数
	 * @author 黄官易
	 * @param str
	 * @return    
	 * @return boolean 
	 * @date 2018年6月22日  
	 * @version 1.0
	 */
	public static final boolean isNaturalNumber(String str) {
		return str.matches("^[1-9]\\d*|0$");
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
		if (str.length() == size) {
			return str;
		}
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
		if (isShort(str, str.length() - fixedHeight)) {
			result = "";
		} else {
			result = str.substring(0, str.length() - fixedHeight);
		}
		return result;
	}

	/**
	 * 
	 * @Title: cutStringLeftRtnInteger
	 * @Description: 左边截取字符串 返回一个Integer 默认值为null
	 * @param str
	 * @param size
	 * @param isFixed
	 * @return
	 * @author: MR.H
	 * @return: Integer
	 *
	 */
	public static final Integer cutStringLeftRtnInteger(String str, int size, boolean isFixed) {
		return Integer.valueOf(isFixed ? cutStringForLeftFixS(str, size) : cutStringForLeft(str, size));
	}

	/**
	 * 
	 * @Title: cutStringRightRtnInteger
	 * @Description: 右边截取字符串 返回一个Integer 默认值为null
	 * @param str
	 * @param size
	 * @param isFixed
	 * @return
	 * @author: MR.H
	 * @return: Integer
	 *
	 */
	public static final Integer cutStringRightRtnInteger(String str, int size, boolean isFixed) {
		return Integer.valueOf(isFixed ? cutStringForRightFixS(str, size) : cutStringForRight(str, size));
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
		String result = "";
		if (isShort(str, str.length() - fixedHeight)) {
			result = "";
		} else {
			result = str.substring(fixedHeight);
		}
		return result;
	}

	/**
	 * 
	 * @Title: isShort
	 * @Description: 判断字符串长度 （小于或等于）
	 * @param str
	 * @param size
	 * @return
	 * @author: MR.H
	 * @return: boolean
	 *
	 */
	public static final boolean isShort(String str, int size) {
		return isBlank(str) ? true : size > str.length();
	}

	/**
	 * 
	 * @Title: notshort
	 * @Description: 判断字符串长度 （大于）
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

	/**
	 * 
	 * @Title: getRandom
	 * @Description: 获得定长随机数
	 * @author 黄官易
	 * @param random
	 * @param size
	 * @return
	 * @return String
	 * @date 2018年6月21日
	 * @version 1.0
	 */
	public static final String getRandom(int random, int size) {
		String randomStr = Integer.toString(random);
		return randomStr.length() > size ? cutStringForRightFixS(randomStr, size) : strAddLeftZero(randomStr, size);
	}
}
