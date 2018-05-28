package com.miaocup.common.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by S on 2018/4/6.
 * @author hejun
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JmsProperties {

    @Value("${rocketmq.name-addr}")
    private String rocketmqNameAddr;

    /**
     * 指令Topic配置
     */
    @Value("${mq.command.topic}")
    private String mqCommandTopic;

    @Value("${mq.command.pid}")
    private String mqCommandPid;

    @Value("${mq.command.cid}")
    private String mqCommandCid;

    /**
     * 退款Topic配置
     */
    @Value("${mq.refund.topic}")
    private String mqRefundTopic;

    @Value("${mq.refund.pid}")
    private String mqRefundPid;

    @Value("${mq.refund.cid}")
    private String mqRefundCid;
}
