package com.yibo.rocketmq.service;

import com.alibaba.fastjson.JSONObject;
import com.yibo.rocketmq.domain.model.AccountChangeEvent;
import com.yibo.rocketmq.mapper.AccountInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 2:20
 * @Description:
 */

@Service
@Slf4j
public class AccountInfoService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 向mq发送转账消息
     * @param accountChangeEvent
     */
    public void sendUpdateAccountBalance(AccountChangeEvent accountChangeEvent){
        //将accountChangeEvent转成json
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        String jsonString = jsonObject.toJSONString();
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();
        //发送一条事务消息
        /**
         * String txProducerGroup 生产组
         * String destination topic，
         * Message<?> message, 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1","topic_txmsg",message,null);
    }

    /**
     * 更新账户，扣减金额
     * @param accountChangeEvent
     */
    @Transactional
    public void doUpdateAccountBalance(AccountChangeEvent accountChangeEvent) {
        //幂等判断
        if(accountInfoMapper.isExistTx(accountChangeEvent.getTxNo())>0){
            return ;
        }
        //扣减金额
        accountInfoMapper.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount() * -1);
        //添加事务日志
        accountInfoMapper.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 3){
            throw new RuntimeException("人为制造异常");
        }
    }
}
