package com.yibo.eureka.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: huangyibo
 * @Date: 2020/12/13 17:28
 * @Description:
 */

@MapperScan("com.yibo.eureka.seata.mapper")//扫描mybatis的指定包下的接口
@SpringBootApplication
@EnableDiscoveryClient  //服务发现，对外暴露服务
@EnableEurekaClient     //本服务启动后会自动注册进Eureka服务中
@EnableFeignClients
public class EurekaSeataBank2Application {

    public static void main(String[] args) {

        SpringApplication.run(EurekaSeataBank2Application.class,args);
    }
}
