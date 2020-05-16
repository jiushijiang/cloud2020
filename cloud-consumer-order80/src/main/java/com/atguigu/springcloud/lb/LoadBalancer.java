package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     * 根据服务器列表获取需要调用的服务器
     * @param serviceInstances
     * @return
     */
    public ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
