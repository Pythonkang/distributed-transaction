package com.yibo.notifypay.service;

import com.yibo.notifypay.domain.entity.AccountPay;
import com.yibo.notifypay.mapper.AccountPayMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 19:26
 * @Description:
 */

@Service
@Slf4j
public class AccountPayService {

    @Autowired
    private AccountPayMapper accountPayMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 插入充值记录
     * @param accountPay
     * @return
     */
    public AccountPay insertAccountPay(AccountPay accountPay) {
        int success = accountPayMapper.insertAccountPay(accountPay.getId(), accountPay.getAccountNo(), accountPay.getPayAmount(), "success");
        if(success>0){
            //发送通知,使用普通消息发送通知
            accountPay.setResult("success");
            rocketMQTemplate.convertAndSend("topic_notifymsg",accountPay);
            return accountPay;
        }
        return null;
    }

    /**
     * 查询充值记录，接收通知方调用此方法来查询充值结果
     * @param txNo
     * @return
     */
    public AccountPay getAccountPay(String txNo) {
        AccountPay accountPay = accountPayMapper.findByIdTxNo(txNo);
        return accountPay;
    }
}
