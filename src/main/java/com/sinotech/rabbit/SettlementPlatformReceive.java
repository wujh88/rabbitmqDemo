package com.sinotech.rabbit;


import com.sinotech.config.ConfigConsts;
import com.sinotech.util.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test_queue_priority")
public class SettlementPlatformReceive {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String json) {
        String rs = new String();
        try {
            //TODO 结算平台的业务处理逻辑
            rs = HttpClient.sendHttpPostJson(ConfigConsts.URL_CHANNEL_PAYAGENT, json);
        } catch (Exception e) {
            logger.error("提交请求串【"+json+"】给渠道层出现异常###"+e.getMessage());
        }
    }
}
