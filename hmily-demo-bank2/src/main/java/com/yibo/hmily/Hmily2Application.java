package com.yibo.hmily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangyibo
 * @Date: 2020/10/11 22:44
 * @Description:
 */

@ComponentScan({"com.yibo.hmily","org.dromara.hmily"})
@MapperScan("com.yibo.hmily.mapper")//扫描mybatis的指定包下的接口
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Hmily2Application {

    public static void main(String[] args) {

        SpringApplication.run(Hmily2Application.class,args);
    }
}
