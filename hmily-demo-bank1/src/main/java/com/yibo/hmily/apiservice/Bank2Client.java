package com.yibo.hmily.apiservice;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: huangyibo
 * @Date: 2020/10/8 20:12
 * @Description:
 */

@FeignClient(value="hmily-bank2")
public interface Bank2Client {

    //远程调用微服务
    @GetMapping("/bank2/transfer/{amount}")
    @Hmily
    public boolean transfer(@PathVariable("amount") Long amount);
}
