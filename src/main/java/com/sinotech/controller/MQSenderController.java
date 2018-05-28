package com.sinotech.controller;

import com.sinotech.common.ResultPackaging;
import com.sinotech.util.log.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;

@Controller
public class MQSenderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MQSenderController.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 多生产多消费
     * @param xml
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public JSONObject testRMQ(@RequestBody String xml) {
        JSONObject json = new JSONObject();
        try {
            this.rabbitTemplate.convertAndSend("sinoPay_async_key", xml);
            json = ResultPackaging.responseSuccess();
        } catch (Exception e) {
            LOGGER.error(LogUtil.format("支付平台回调MQ","回调参数加入MQ出现异常",xml,e.getMessage()));
        }
        return json;
    }

    /**
     * 多生产单消费
     * @param xml
     * @return
     */
    @RequestMapping("/testN1")
    @ResponseBody
    public JSONObject testN1(@RequestBody String xml) {
        JSONObject json = new JSONObject();
        try {
            this.rabbitTemplate.convertAndSend("test_queue_priority", (Object) xml, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setPriority(1);
                    return message;
                }
            });
            json = ResultPackaging.responseSuccess();
        } catch (Exception e) {
            LOGGER.error(LogUtil.format("结算平台业务请求MQ","参数加入MQ出现异常",xml,e.getMessage()));
        }
        return json;
    }
}
