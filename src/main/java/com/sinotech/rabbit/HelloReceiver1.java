package com.sinotech.rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinotech.config.ConfigConsts;
import com.sinotech.service.MQsnService;
import com.sinotech.util.HttpClient;
import com.sinotech.util.JsonUtil;

@Component
@RabbitListener(queues = "sinoPay_async_key",containerFactory = "pointTaskContainerFactory")
public class HelloReceiver1 {
    private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	public MQsnService mQsnService;
//	@Autowired
//    private AmqpTemplate rabbitTemplate;
	
    @RabbitHandler
    public void process(String json) {
    	String rs = new String();
    	try {
//    		logger.info(json);
//    		System.out.println(json);
			rs = HttpClient.sendHttpPostJson(ConfigConsts.URL_CHANNEL_PAYAGENT, json);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("提交请求串【"+json+"】给渠道层出现异常###"+e.getMessage());
		}
//    	if (rs.isEmpty() || rs == null || !"1".equals(rs)) {
//    		logger.info("提交给渠道层出现异常后，再次将记录放入MQ中消费，json="+json);
//    		this.rabbitTemplate.convertAndSend("test_queue_key00", json);
//		}
//    	if ("error".equals(JsonUtil.toJSONObject(rs).getString("result"))) {
//    		try {
//				HttpClient.sendHttpPostJson(ConfigConsts.URL_CORE_PAYAGENT_CALLBACK, rs);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				logger.error("失败信息【"+rs+"】同步返回给core回调接口出现异常###"+e.getMessage());
//			}
//		}
    }

}
