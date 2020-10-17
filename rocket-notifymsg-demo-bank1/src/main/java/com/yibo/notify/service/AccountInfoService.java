package com.yibo.notify.service;

import com.yibo.notify.domain.entity.AccountPay;
import com.yibo.notify.domain.model.AccountChangeEvent;
import com.yibo.notify.feign.PayClient;
import com.yibo.notify.mapper.AccountInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 19:36
 * @Description:
 */

@Service
@Slf4j
public class AccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private PayClient payClient;

    /**
     * 更新账户金额
     * @param accountChange
     */
    @Transactional
    public void updateAccountBalance(AccountChangeEvent accountChange) {
        //幂等校验
        if(accountInfoMapper.isExistTx(accountChange.getTxNo())>0){
            return ;
        }
        int i = accountInfoMapper.updateAccountBalance(accountChange.getAccountNo(), accountChange.getAmount());
        //插入事务记录，用于幂等控制
        accountInfoMapper.addTx(accountChange.getTxNo());
    }

    /**
     * 远程调用查询充值结果
     * @param tx_no
     * @return
     */
    public AccountPay queryPayResult(String tx_no) {

        //远程调用
        AccountPay payresult = payClient.payresult(tx_no);
        if("success".equals(payresult.getResult())){
            //更新账户金额
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(payresult.getAccountNo());//账号
            accountChangeEvent.setAmount(payresult.getPayAmount());//金额
            accountChangeEvent.setTxNo(payresult.getId());//充值事务号
            updateAccountBalance(accountChangeEvent);
        }
        return payresult;
    }
}
