package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsumerMain83 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentConsumerMain83.class,args);
    }
}
