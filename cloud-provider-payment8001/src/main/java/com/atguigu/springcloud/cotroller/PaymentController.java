package com.atguigu.springcloud.cotroller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result=paymentService.create(payment);

        log.info("**************"+result+"*****************");

        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }

        return new CommonResult(400,"插入失败！",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        Payment payment=paymentService.getPaymentById(id);

        log.info("**************查询到的数据为"+payment+"*****************");

        if (payment != null){
            return new CommonResult(200,"查找成功",payment);
        }

        return new CommonResult(400,"查找失败！",null);
    }
}