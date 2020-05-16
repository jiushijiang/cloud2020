package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2020-02-25 18:30
 */
@Component
public class PaymentFallbackService implements PaymentService
{

    @Override
    public String getPayment(Integer id) {

        return "调用失败："+id;
    }
}
