package com.yibo.notifypay.controller;

import com.yibo.notifypay.domain.entity.AccountPay;
import com.yibo.notifypay.service.AccountPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 19:25
 * @Description:
 */

@RestController
@RequestMapping("/account")
public class AccountPayController {

    @Autowired
    private AccountPayService accountPayService;

    //充值
    @GetMapping(value = "/paydo")
    public AccountPay pay(AccountPay accountPay){
        //生成事务编号
        String txNo = UUID.randomUUID().toString();
        accountPay.setId(txNo);
        return accountPayService.insertAccountPay(accountPay);
    }

    //查询充值结果
    @GetMapping(value = "/payResult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo){
        return accountPayService.getAccountPay(txNo);
    }
}
