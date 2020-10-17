package com.yibo.seata.controller;

import com.yibo.seata.service.AccountService;
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
@RequestMapping("/bank1")
public class Bank1Controller {

    @Autowired
    private AccountService accountService;

    @GetMapping("/transfer/{amount}")
    public String transfer(@PathVariable("amount") Long amount){
        accountService.updateAccountBalance("1",amount);
        return "bank1"+amount;
    }
}
