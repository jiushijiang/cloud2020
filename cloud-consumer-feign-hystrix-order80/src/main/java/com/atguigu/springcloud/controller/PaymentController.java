package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback ="payment_Global_FallbackMethod" )//全局注解
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getOk(@PathVariable("id") Integer id){
        String result = paymentService.getOk(id);
        log.info("*****consumer result:" + result);
        return result;

    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//
//    })//局部注解
    @HystrixCommand//全局注解
    public String getTimeout(@PathVariable("id") Integer id){
        //1.程序超时
        //2.程序报错
        int i=10/0;

        String result = paymentService.getTimeout(id);
        log.info("*****consumer result:" + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout01/{id}")
    public String getTimeout01(@PathVariable("id") Integer id){
        //1.程序超时
        //2.程序报错
        //int i=10/0;

        String result = paymentService.getTimeout(id);
        log.info("*****consumer result:" + result);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后重试.o(╥﹏╥)o";
    }
}
