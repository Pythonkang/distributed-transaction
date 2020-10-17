package com.yibo.seata.apiservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: huangyibo
 * @Date: 2020/10/8 20:12
 * @Description:
 */

@FeignClient(value="seata-bank2")
public interface Bank2Client {

    //远程调用微服务
    @GetMapping("/bank2/transfer/{amount}")
    public String transfer(@PathVariable("amount")Long amount);
}
