package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther zzyy
 * @create 2020-02-25 18:15
 */
@FeignClient("nacos-payment-provider")
@Component
public interface PaymentService
{

    @GetMapping("/paymentSQL/{id}")
    public String getPayment(@PathVariable("id") Integer id);

}
