package com.atguigu.springcloud.handler;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.PathVariable;

public class PaymentFallbackHandler {

    //记得写static，耗费了我一个小时
    public static CommonResult<Payment> paymentFallback(@PathVariable Long id, Throwable throwable){

//        return new CommonResult<>(444,"系统错误，调用降级方法：--------paymentFallback,原因："+throwable.getMessage());
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+throwable.getMessage(),payment);
    }
}
