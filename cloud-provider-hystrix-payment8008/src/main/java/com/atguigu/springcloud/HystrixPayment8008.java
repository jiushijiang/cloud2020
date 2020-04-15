package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient//2.使用客户端必须要开启的
@EnableHystrix
public class HystrixPayment8008 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixPayment8008.class,args);
    }
}
