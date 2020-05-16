package com.atguigu.springcloud.lb.impl;

import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LB implements LoadBalancer {

    private AtomicInteger integer=new AtomicInteger(0);

    public final Integer getAndIncrease(){

        int current;
        int next;

        do {
            current=this.integer.get();

            next=current >= 2147483647 ? 0 :current+1;


        }while (!this.integer.compareAndSet(current,next));
        System.out.println("next*****************"+next);

        return next;

    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
       int index= getAndIncrease() %serviceInstances.size();

        return serviceInstances.get(index);
    }
}
