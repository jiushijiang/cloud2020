package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class PaymentService {

    public String getOk(Integer id){

        return "返回的线程名为："+ Thread.currentThread().getName()+",编号为："+id+"笑脸";

    }
    @HystrixCommand(fallbackMethod ="getTimeoutHandler" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String getTimeout(Integer id){

        Integer timeout=5000;
        //演示程序超时
        try {
            Thread.sleep(timeout);

        }catch (Exception e){
            e.printStackTrace();
        }
        //2.演示程序报错
        //int i=10/0;

        return "返回的线程名为："+ Thread.currentThread().getName()+",编号为："+id+"延迟时间为："+timeout+",调用端口：8001";


    }

    public String getTimeoutHandler(Integer id){


        return "返回的线程名"+ Thread.currentThread().getName()+"调用的方法名为：getTimeoutHandler,编号为："+id+"o(╥﹏╥)o"+",调用端口：8001";


    }
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_break",commandProperties = {
        @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//开启熔断
        @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//总次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//等待窗口时间：10s
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败概率为60的时候开启熔断
    })
    public String paymentCircuitBreaker(Integer id){

        if (id < 0){
            throw new RuntimeException("*********id不能为负数*********");
        }

        String random= UUID.randomUUID().toString().replace("-","");
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+random;
    }

    public String paymentCircuitBreaker_break(Integer id){


        return "返回的线程名"+ Thread.currentThread().getName()+"调用的方法名为：paymentCircuitBreaker_break,编号为："+id+"o(╥﹏╥)o"+",调用端口：8001";


    }
}
