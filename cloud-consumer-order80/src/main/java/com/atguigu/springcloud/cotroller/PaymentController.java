package com.atguigu.springcloud.cotroller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;

import com.atguigu.springcloud.lb.LoadBalancer;
import com.netflix.loadbalancer.IRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    private static final String PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate template;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;



    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {

        //System.out.println(iRule.getClass().getName());
        return template.postForObject(PROVIDER_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        return template.getForObject(PROVIDER_URL + "/payment/get/" + id, CommonResult.class);
    }

    /**
     * 使用getEntity
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getEntity(@PathVariable("id") Long id) {

        ResponseEntity<CommonResult> responseEntity = template.getForEntity(PROVIDER_URL + "/payment/get/" + id, CommonResult.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    /**
     * 使用postEntity
     *
     * @return
     */
    @GetMapping("/consumer/payment/postEntity")
    public CommonResult<Payment> postEntity() {

        Payment payment = new Payment();
        payment.setSerial("2222");

        ResponseEntity<CommonResult> responseEntity = template.postForEntity(PROVIDER_URL + "/payment/create", payment, CommonResult.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }


    /**
     *
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> serviceInstances=discoveryClient.
                getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.size()<=0){

            return null;

        }
        ServiceInstance serviceInstance = loadBalancer.instance(serviceInstances);

        URI uri = serviceInstance.getUri();

        String str = template.getForObject(uri + "/payment/lb", String.class);


        return str;


    }


}