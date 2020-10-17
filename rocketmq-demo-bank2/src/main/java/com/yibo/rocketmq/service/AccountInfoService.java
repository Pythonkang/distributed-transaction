package com.yibo.rocketmq.service;

import com.yibo.rocketmq.domain.model.AccountChangeEvent;
import com.yibo.rocketmq.mapper.AccountInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 3:38
 * @Description:
 */

@Service
@Slf4j
public class AccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 更新账户，增加金额
     * @param accountChangeEvent
     */
    @Transactional
    public void addAccountInfoBalance(AccountChangeEvent accountChangeEvent) {
        log.info("bank2更新本地账号，账号：{},金额：{}",accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        if(accountInfoMapper.isExistTx(accountChangeEvent.getTxNo())>0){
            return ;
        }

        //增加金额
        accountInfoMapper.updateAccountBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());

        //添加事务记录，用于幂等
        accountInfoMapper.addTx(accountChangeEvent.getTxNo());
        if(accountChangeEvent.getAmount() == 4){
            throw new RuntimeException("人为制造异常");
        }
    }
}
