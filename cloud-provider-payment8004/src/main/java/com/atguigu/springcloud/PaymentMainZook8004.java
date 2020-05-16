package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//该注解使用zookeeper和consul注册
@SpringBootApplication
public class PaymentMainZook8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMainZook8004.class,args);
    }
}
