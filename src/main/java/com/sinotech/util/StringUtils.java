package com.sinotech.util;

 
/**
* String工具类
* @author ZTF
* @date 2017年4月26日 上午10:21:13
**/
public class StringUtils {
	
	/**
	 * 判断多个字符串是否包含空值
	 * @param val 
	 * @param values 可变长度参数
	 * @return   有空值返回：true，无空值返回：false
	 * @author ZTF
	 * @date 2017年4月26日 上午10:59:00
	 */
	public static boolean isEmpty(String val,String... values) {
		if (isEmpty(val)) {
			return true;
		}
		if (values == null || values.length == 0) {
			return true;
		}
		for (String string : values) {
			 if (isEmpty(string)) {
		            return true;
		        }
		}
		
		return false;
	}
	
	public static boolean isEmpty(String value) {
        if (value == null || value.length() == 0 || "null".equals(value)) {
            return true;
        }

		return false;
	}
	
    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        }
        
        return a.equals(b);
    }
	
}
