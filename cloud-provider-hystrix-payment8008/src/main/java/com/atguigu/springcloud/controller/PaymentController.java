package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String getOk(@PathVariable("id") Integer id){
        String result = paymentService.getOk(id);
        log.info("*****result:" + result);
        return result;

    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String getTimeout(@PathVariable("id") Integer id){
        String result = paymentService.getTimeout(id);
        log.info("*****result:" + result);
        return result;

    }


    @GetMapping("/payment/hystrix/circuit/{id}")
    public String circuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result:" + result);
        return result;

    }

}
