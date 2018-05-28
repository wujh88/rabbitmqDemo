package com.sinotech.util.log;

import java.util.Date;

import com.sinotech.util.DateUtil;

/**
 * 日志处理封装工具类
 * @author Haakon 2017-05-09
 *
 */
public class LogUtil {

	/**
	 * 封装输出日志中的类名和时间戳
	 * @param clazz
	 * @return
	 */
	public static String logStr(Class<?>  clazz) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("##").append(clazz).append("##").append(DateUtil.getDateTimeMilFormat(new Date()));
		return sBuffer.toString();
	}

	/**
	 *	格式化日志内容
	 *
	 *  OMS##订单新增接口addOrder()##传入参数param=····
	 * 系统##接口名#日志内容##日志生成时间##操作者？
	 * @param systemName 系统名
	 * @param moduleAndMethod  模块和方法名
	 * @param msgAndParams	消息和请求参数
	 * @param exMsg 异常信息
	 * @return
	 */
	public static String format(String systemName, String moduleAndMethod,String msgAndParams, String exMsg){

		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append(systemName)
				.append("##")
				.append(moduleAndMethod)
				.append("##")
				.append(msgAndParams)
				.append("##")
				.append(exMsg)
				.append("##")
				.append(DateUtil.getDateTimeMilFormat(new Date()));
		return stringBuffer.toString();
	}
}
