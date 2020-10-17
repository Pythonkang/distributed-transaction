package com.yibo.notify.feign;

import com.yibo.notify.domain.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 19:46
 * @Description:
 */

@FeignClient(value = "notify-msg-pay")
public interface PayClient {

    //远程调用充值系统的接口查询充值结果
    @GetMapping(value = "account/payResult/{txNo}")
    public AccountPay payresult(@PathVariable("txNo") String txNo);
}
