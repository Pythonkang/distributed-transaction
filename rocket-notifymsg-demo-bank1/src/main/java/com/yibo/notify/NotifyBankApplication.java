package com.yibo.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 19:33
 * @Description:
 */

@MapperScan("com.yibo.notify.mapper")//扫描mybatis的指定包下的接口
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class NotifyBankApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotifyBankApplication.class,args);
    }
}
