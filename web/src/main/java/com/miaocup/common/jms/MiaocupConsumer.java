package com.miaocup.common.jms;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.miaocup.common.contants.MiaocupContants;

/**
 * @author beihui.hejun
 * @version V1.0
 * @Description: MQ消费者
 * @date 2018/4/5y
 */
public class MiaocupConsumer extends DefaultMQPushConsumer {

    public MiaocupConsumer(String topic) throws MQClientException {
        this.subscribe(topic, MiaocupContants.MQ_TOPIC_TAG);
    }
}
