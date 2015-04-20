package com.butterfly.util;

public class StringUtil {
	
	public static String notNull(String s){
        if(s==null||s.length()<1)
            return "";
        return s;
    }
	
	public static String convert(int param) {
		return param == 0 ? "否" : "是";
	}
	
	public static String convertSex(int param) {
		return param == 0 ? "男" : "女";
	}

	public static boolean isEmpty(String str){
		if("".equals(str)||str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
}
