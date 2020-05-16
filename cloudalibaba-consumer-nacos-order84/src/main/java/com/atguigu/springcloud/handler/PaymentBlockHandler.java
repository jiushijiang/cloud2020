package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.PathVariable;

public class PaymentBlockHandler {

    public static CommonResult<Payment> getByRestTemplate_error(@PathVariable Long id, BlockException blockException){

        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流 ");
        //return new CommonResult<>(666,"流量超出限制，调用降级方法：--------paymentSQLHandler,原因："+blockException.getMessage());
    }

}
