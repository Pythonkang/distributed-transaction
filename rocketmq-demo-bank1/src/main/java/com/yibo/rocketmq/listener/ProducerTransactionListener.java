package com.yibo.rocketmq.listener;

import com.alibaba.fastjson.JSONObject;
import com.yibo.rocketmq.domain.model.AccountChangeEvent;
import com.yibo.rocketmq.mapper.AccountInfoMapper;
import com.yibo.rocketmq.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 3:21
 * @Description:
 */

@Component
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1")
@Slf4j
public class ProducerTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
     * @param message
     * @param o
     * @return
     */
    @Transactional
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");
            //将accountChange（json）转成AccountChangeEvent
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
            //执行本地事务，扣减金额
            accountInfoService.doUpdateAccountBalance(accountChangeEvent);
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            log.error("ProducerTransactionListener execute Exception",e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 事务状态回查，查询是否扣减金额
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("accountChange");
        //将accountChange（json）转成AccountChangeEvent
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
        //事务id
        String txNo = accountChangeEvent.getTxNo();
        int existTx = accountInfoMapper.isExistTx(txNo);
        if(existTx>0){
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
