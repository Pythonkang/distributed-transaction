package com.yibo.rocketmq.controller;

import com.yibo.rocketmq.domain.model.AccountChangeEvent;
import com.yibo.rocketmq.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 3:33
 * @Description:
 */

@RestController
@Slf4j
@RequestMapping("/rocket1")
public class AccountInfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping(value = "/transfer")
    public String transfer(@RequestParam("accountNo")String accountNo, @RequestParam("amount") Double amount){
        //创建一个事务id，作为消息内容发到mq
        String tx_no = UUID.randomUUID().toString();
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent(accountNo,amount,tx_no);
        //发送消息
        accountInfoService.sendUpdateAccountBalance(accountChangeEvent);
        return "转账成功";
    }
}
