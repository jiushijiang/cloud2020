package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;

import com.atguigu.springcloud.handler.CustomerBlockHandler;
import com.atguigu.springcloud.handler.PaymentBlockHandler;
import com.atguigu.springcloud.handler.PaymentFallbackHandler;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-25 16:05
 */
@RestController
@Slf4j
public class CircleBreakerController {
    private final String requestUrl = "http://nacos-payment-provider";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;


    @GetMapping(value = "/getMessage/{id}")
    @SentinelResource(value = "paymentSQL", blockHandlerClass = PaymentBlockHandler.class, blockHandler = "getByRestTemplate_error")
    public String getMessage(@PathVariable Integer id) {

        return paymentService.getPayment(id);

    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "customer_error")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "自定义限流规则OK", new Payment(2020L, "serial003"));
    }

    /**
     * 使用restTemplate调用方法
     */
    @GetMapping("/consumer/paymentSQL/{id}")
    @SentinelResource(value = "paymentSQL",
            blockHandlerClass = PaymentBlockHandler.class, blockHandler = "paymentBlockHandler"
            , fallbackClass = PaymentFallbackHandler.class, fallback = "paymentFallback"
    )
    // @SentinelResource(value= "paymentSQL", blockHandlerClass = PaymentBlockHandler.class,blockHandler = "getByRestTemplate_error")
    public CommonResult<Payment> getByRestTemplate(@PathVariable Long id) {

        if (id == 4) {
            throw new IllegalArgumentException("参数不合法");
        }

        return restTemplate.getForObject(requestUrl + "/paymentSQL/" + id, CommonResult.class, id);

    }

    //@SentinelResource(value="paymentSQL",fallback ="paymentFallback",blockHandler = "paymentSQLHandler")
//    @SentinelResource(value="paymentSQL",
//            blockHandlerClass = PaymentBlockHandler.class,blockHandler = "paymentBlockHandler"
//   //         ,fallbackClass = PaymentFallbackHandler.class,fallback = "paymentFallback"
//            )
//    @SentinelResource(value = "paymentSQL",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "customer_error")


}
