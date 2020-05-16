package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String getOk(Integer id) {
        return "-----------------PaymentFallbackService ok----------------------";
    }

    @Override
    public String getTimeout(Integer id) {
        return "-----------------PaymentFallbackService getTimeout----------------------";
    }
}
