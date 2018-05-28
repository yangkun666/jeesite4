package com.miaocup.common.jms;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author beihui.hejun
 * @version V1.0
 * @Description: MQ消费者监听
 * @date 2018/4/6
 */
public abstract class MiaocupListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        return consume(list.get(0), consumeConcurrentlyContext);
    }

    public abstract ConsumeConcurrentlyStatus consume(Message message, ConsumeConcurrentlyContext consumeConcurrentlyContext);
}
