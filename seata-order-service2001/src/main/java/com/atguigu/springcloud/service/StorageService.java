package com.atguigu.springcloud.service;

import com.atguigu.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
@Component
public interface StorageService {

    @PostMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);

}
