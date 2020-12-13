package com.yibo.eureka.seata.service.impl;

import com.yibo.eureka.seata.apiservice.Bank2Client;
import com.yibo.eureka.seata.mapper.AccountInfoMapper;
import com.yibo.eureka.seata.service.AccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/9 0:02
 * @Description:
 */

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private Bank2Client bank2Client;

    @Transactional
    @GlobalTransactional//开启全局事务
    public void updateAccountBalance(String accountNo, Long amount) {
        log.info("bank1 service begin,XID：{}", RootContext.getXID());
        //扣减张三的金额
        accountInfoMapper.updateAccountBalance(accountNo,amount * -1);
        //调用李四微服务，转账
        String transfer = bank2Client.transfer(amount);
        if("fallback".equals(transfer)){
            //调用李四微服务异常
            throw new RuntimeException("调用李四微服务异常");
        }
        if(amount == 2){
            //人为制造异常
            throw new RuntimeException("bank1 make exception..");
        }
    }
}
