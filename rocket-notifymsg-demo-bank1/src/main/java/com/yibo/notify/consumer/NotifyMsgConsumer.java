package com.yibo.notify.consumer;

import com.alibaba.fastjson.JSON;
import com.yibo.notify.domain.entity.AccountPay;
import com.yibo.notify.domain.model.AccountChangeEvent;
import com.yibo.notify.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 20:33
 * @Description:
 */

@Component
@Slf4j
@RocketMQMessageListener(topic = "topic_notifymsg",consumerGroup = "consumer_group_notifymsg_bank1")
public class NotifyMsgConsumer implements RocketMQListener<AccountPay> {

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public void onMessage(AccountPay accountPay) {
        log.info("接收到消息：{}", JSON.toJSONString(accountPay));
        if("success".equals(accountPay.getResult())){
            //更新账户金额
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountPay.getAccountNo());
            accountChangeEvent.setAmount(accountPay.getPayAmount());
            accountChangeEvent.setTxNo(accountPay.getId());
            accountInfoService.updateAccountBalance(accountChangeEvent);
        }
        log.info("处理消息完成：{}", JSON.toJSONString(accountPay));
    }
}
