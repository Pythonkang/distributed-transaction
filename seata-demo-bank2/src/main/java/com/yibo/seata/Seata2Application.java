package com.yibo.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangyibo
 * @Date: 2020/10/8 19:30
 * @Description:
 */

@MapperScan("com.yibo.seata.mapper")//扫描mybatis的指定包下的接口
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class Seata2Application {

    public static void main(String[] args) {

        SpringApplication.run(Seata2Application.class,args);
    }
}
