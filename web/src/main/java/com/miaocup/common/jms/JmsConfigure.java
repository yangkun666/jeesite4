package com.miaocup.common.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author beihui.hejun
 * @version V1.0
 * @Description: RocketMQ配置
 * @date 2018/4/5
 */
@Configuration
public class JmsConfigure {

    @Autowired
    private JmsProperties jmsProperties;

    @Bean(initMethod = "start", destroyMethod = "shutdown", name = "commandProducer")
    public MiaocupProducer commandProducer() {
        MiaocupProducer producer = new MiaocupProducer(jmsProperties.getMqCommandTopic());
        producer.setNamesrvAddr(jmsProperties.getRocketmqNameAddr());
        producer.setProducerGroup(jmsProperties.getMqCommandPid());
        return producer;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown", name = "refundProducer")
    public MiaocupProducer refundProducer() {
        MiaocupProducer producer = new MiaocupProducer(jmsProperties.getMqRefundTopic());
        producer.setNamesrvAddr(jmsProperties.getRocketmqNameAddr());
        producer.setProducerGroup(jmsProperties.getMqRefundPid());
        return producer;
    }

}
