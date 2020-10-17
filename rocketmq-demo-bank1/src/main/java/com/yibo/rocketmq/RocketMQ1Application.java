package com.yibo.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangyibo
 * @Date: 2020/10/17 1:54
 * @Description:
 */

@EnableDiscoveryClient
@MapperScan("com.yibo.rocketmq.mapper")//扫描mybatis的指定包下的接口
@SpringBootApplication
public class RocketMQ1Application {

    public static void main(String[] args) {

        SpringApplication.run(RocketMQ1Application.class,args);
    }
}
