package com.atguigu.springcloud.cotroller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class PaymentController {

    private static final String  PROVIDER_URL="http://cloud-provider-payment";

    @Autowired
    private RestTemplate template;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        return template.getForObject(PROVIDER_URL + "/payment/zk", String.class);
    }


}