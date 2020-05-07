package com.atguigu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult customer_error(BlockException blockException){

        return new CommonResult(444,"customerBlockHandler 服务不可用");
    }
}
