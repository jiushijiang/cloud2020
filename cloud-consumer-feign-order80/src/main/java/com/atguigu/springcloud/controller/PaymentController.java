package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/payment/get/{id}",produces = { "application/json;charset=UTF-8" })
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){


        CommonResult<Payment> commonResult = paymentService.getPaymentById(id);

        return commonResult;
    }

    @GetMapping(value = "/consumer/payment/feignTimeOut")
    public String feignTimeOut(){
        String port = paymentService.feignTimeOut();
        return port;
    }
}
