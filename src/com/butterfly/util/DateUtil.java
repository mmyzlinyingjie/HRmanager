package com.butterfly.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String yyyyMMdd="yyyy-MM-dd";	//长日期格式

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	public static Date formatString(String str, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	public static String parseToString(Date date, String style) {// 日期时间转换成字符串
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(); // 实例化日期格式化类
		simpleDateFormat.applyPattern(style); // 提交转换格式
		String str = null;
		if (date == null) // 如果日期时间为null
			return null; // 返回null值
		str = simpleDateFormat.format(date); // 将日期格式化为字符串
		return str;
	}
}
