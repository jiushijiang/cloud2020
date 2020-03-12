package com.atguigu.springcloud.cotroller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class PaymentController {

    private static final String  PROVIDER_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate template;

    @GetMapping("/consumer/payment/create")
    public CommonResult create( Payment payment){



        return template.postForObject(PROVIDER_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return template.getForObject(PROVIDER_URL+"/payment/get/"+id,CommonResult.class);
    }
}