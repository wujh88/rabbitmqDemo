package com.sinotech.common;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import com.sinotech.config.FailCodeConstants;


/**
 * 封装公共的返回函数
 * @author haakon
 *
 */
@Component
public class ResultPackaging {

	/**
	 * 获取系统自定义错误编码及错误信息
	 * @param failcode
	 * @param result
	 * @return   
	 * @author ZTF
	 * @date 2017年5月9日 下午1:49:42
	 */
	public static JSONObject getFailCodeAndMsg(String failcode,String msg){
		JSONObject json = responseFailJson(FailCodeConstants.RESULT_CODE_FAIL,failcode);
		json.put("msg", msg);
		return json;
		
	}
	
	
	
	/**
	 * 通知成功的反馈报文
	 * @return   
	 * @author ZTF
	 * @date 2017年5月10日 上午11:04:56
	 */
	public static JSONObject responseSuccess(){
		JSONObject response = new JSONObject();
		response.put("result", FailCodeConstants.RESPONSE_SUCCESS);
		response.put("msg","请求成功");
		return response;
		
	}
	
	/**
	 * 通知失败的反馈报文
	 * @return   
	 * @author Haakon
	 * @date 2017年5月10日 上午11:04:56
	 */
	public static JSONObject responseFail(){
		JSONObject response = new JSONObject();
		response.put("result", FailCodeConstants.RESULT_CODE_FAIL);
		response.put("msg","请求失败");
		return response;
		
	}
	
	private static JSONObject responseFailJson(String result, String code){
		JSONObject response = new JSONObject();
		response.put("result", result);
		response.put("code",code);
		return response;
	}
	
}
