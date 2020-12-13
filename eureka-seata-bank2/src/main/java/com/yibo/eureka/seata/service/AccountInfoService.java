package com.yibo.eureka.seata.service;


import com.yibo.eureka.seata.mapper.AccountInfoMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: huangyibo
 * @Date: 2020/10/8 20:14
 * @Description:
 */

@Service
@Slf4j
public class AccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Transactional
    public void updateAccountBalance(String accountNo, Long amount) {
        log.info("bank2 service begin,XID：{}",RootContext.getXID());
        //李四增加金额
        accountInfoMapper.updateAccountBalance(accountNo,amount);
        if(amount==3){
            //人为制造异常
            throw new RuntimeException("bank2 make exception..");
        }
    }
}
