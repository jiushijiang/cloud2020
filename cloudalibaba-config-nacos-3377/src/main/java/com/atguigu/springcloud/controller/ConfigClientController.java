package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//热刷新注解，实时刷新配置
public class ConfigClientController
{
    @Value("${config.name}")
    private String configInfo;

    @GetMapping("/nacos/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}