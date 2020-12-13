package com.yibo.eureka.seata.controller;

import com.yibo.eureka.seata.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangyibo
 * @Date: 2020/10/8 20:13
 * @Description:
 */

@RestController
@RequestMapping("/bank2")
public class Bank2Controller {

    @Autowired
    private AccountInfoService accountInfoService;

    //张三转账
    @GetMapping("/transfer/{amount}")
    public String transfer(@PathVariable("amount") Long amount){
        accountInfoService.updateAccountBalance("2",amount);
        return "bank1"+amount;
    }
}
