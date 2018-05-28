package com.miaocup.common.jms;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.miaocup.common.contants.MiaocupContants;
import com.miaocup.uuid.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author beihui.hejun
 * @version V1.0
 * @Description: MQ生产者
 * @date 2018/4/5
 */
public class MiaocupProducer extends DefaultMQProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String topic;

    MiaocupProducer(String topic) {
        this.topic = topic;
    }

    public void sendMsg(String content) {
        Message msg = new Message(topic, MiaocupContants.MQ_TOPIC_TAG, content.getBytes());
        String keys = UUIDUtil.uuid();
        msg.setKeys(keys);
        try {
            SendResult sendResult = this.send(msg);
            assert sendResult != null;
            logger.info("消息发送成功，keys：{},msgId：{} ,topic：{}", keys, sendResult.getMsgId(), topic);
        } catch (Exception e) {
            logger.error("消息发送失败，Topic is {}，cause by ", topic, e);
            throw new RuntimeException(e);
        }
    }
}
