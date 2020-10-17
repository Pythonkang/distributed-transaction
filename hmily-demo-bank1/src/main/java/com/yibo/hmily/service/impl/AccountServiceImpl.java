package com.yibo.hmily.service.impl;

import com.yibo.hmily.apiservice.Bank2Client;
import com.yibo.hmily.mapper.AccountInfoMapper;
import com.yibo.hmily.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
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

    /**
     * 账户扣款，就是tcc的try方法
     * 	try幂等校验
     * 	try悬挂处理
     * 	检查余额是够扣减金额
     * 	扣减金额
     * @param accountNo
     * @param amount
     */
    @Transactional
    //只要标记@Hmily就是try方法，在注解中指定confirm、cancel两个方法的名字
    @Hmily(confirmMethod="commit",cancelMethod="rollback")
    public void updateAccountBalance(String accountNo, Long amount) {
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 try begin 开始执行...xid:{}",transId);

        //幂等判断 判断local_try_log表中是否有try日志记录，如果有则不再执行
        if(accountInfoMapper.isExistTry(transId) > 0){
            log.info("bank1 try 已经执行，无需重复执行,xid:{}",transId);
            return ;
        }

        //try悬挂处理，如果cancel、confirm有一个已经执行了，try不再执行
        if(accountInfoMapper.isExistConfirm(transId) > 0 || accountInfoMapper.isExistCancel(transId) > 0){
            log.info("bank1 try悬挂处理  cancel或confirm已经执行，不允许执行try,xid:{}",transId);
            return ;
        }

        //扣减金额
        if(accountInfoMapper.subtractAccountBalance(accountNo, amount)<=0){
            //扣减失败
            throw new RuntimeException("bank1 try 扣减金额失败,xid:{}"+transId);
        }

        //插入try执行记录,用于幂等判断
        accountInfoMapper.addTry(transId);

        //远程调用转账
        if(!bank2Client.transfer(amount)){
            throw new RuntimeException("bank1 远程调用李四微服务失败,xid:{}"+transId);
        }

        if(amount == 2){
            throw new RuntimeException("人为制造异常,xid:{}"+transId);
        }
        log.info("bank1 try end 结束执行...xid:{}",transId);
    }

    //confirm方法
    @Transactional
    public void commit(String accountNo, Double amount){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 confirm begin 开始执行...xid:{},accountNo:{},amount:{}",transId,accountNo,amount);
    }

    /** cancel方法
     * 	cancel幂等校验
     * 	cancel空回滚处理
     * 	增加可用余额
     * @param accountNo
     * @param amount
     */
    @Transactional
    public void rollback(String accountNo, Long amount){
        //获取全局事务id
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("bank1 cancel begin 开始执行...xid:{}",transId);
        //	cancel幂等校验
        if(accountInfoMapper.isExistCancel(transId) > 0){
            log.info("bank1 cancel 已经执行，无需重复执行,xid:{}",transId);
            return ;
        }

        //cancel空回滚处理，如果try没有执行，cancel不允许执行
        if(accountInfoMapper.isExistTry(transId)<=0){
            log.info("bank1 空回滚处理，try没有执行，不允许cancel执行,xid:{}",transId);
            return ;
        }

        //增加可用余额
        accountInfoMapper.addAccountBalance(accountNo,amount);
        //插入一条cancel的执行记录
        accountInfoMapper.addCancel(transId);
        log.info("bank1 cancel end 结束执行...xid:{}",transId);
    }
}
