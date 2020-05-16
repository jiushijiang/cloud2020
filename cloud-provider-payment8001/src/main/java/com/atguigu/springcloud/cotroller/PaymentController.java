package com.atguigu.springcloud.cotroller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;
    //服务发现中获取服务列表
    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        int result=paymentService.create(payment);

        log.info("**************"+result+"*****************");

        if (result>0){
            return new CommonResult(200,"插入数据库成功,port:"+port,result);
        }

        return new CommonResult(400,"插入失败！",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        Payment payment=paymentService.getPaymentById(id);

        log.info("**************查询到的数据为"+payment+"*****************");

        if (payment != null){
            return new CommonResult(200,"查找成功,port:"+port,payment);
        }

        return new CommonResult(400,"查找失败！",null);
    }
    @GetMapping("/payment/get/getDiscoveryClient")
    public Object getDiscoveryClient(){

        List<String> services = discoveryClient.getServices();

        for (String service : services) {

            log.info("***************"+service+"*******************");
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"**"+instance.getHost()+"**"+instance.getPort()+"**"
            +instance.getUri());
        }
        return discoveryClient;


    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return port;
    }


    @GetMapping(value = "/payment/feignTimeOut")
    public String feignTimeOut(){

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        return port;
    }

}