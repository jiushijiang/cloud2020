package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.stereotype.Service;

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
}
