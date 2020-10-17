package com.yibo.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.yibo.rocketmq.domain.model.AccountChangeEvent;
import com.yibo.rocketmq.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 3:41
 * @Description:
 */

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsg_bank2",topic = "topic_txmsg")
public class TransactionConsumer implements RocketMQListener<AccountChangeEvent> {

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public void onMessage(AccountChangeEvent accountChangeEvent) {
        log.info("开始消费消息:{}", JSON.toJSONString(accountChangeEvent));
        //设置账号为李四的
        accountChangeEvent.setAccountNo("2");
        //更新本地账户，增加金额
        accountInfoService.addAccountInfoBalance(accountChangeEvent);
    }
}
