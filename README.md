# cloud2020
    -- cloud-api-common：主要用于存储一些公共的部分，不需要启动，但是需要要install到本地
    -- cloud-consumer-order80：消费者，用于调用服务，添加ribbon支持
    -- cloud-eureka-server7001:用于对服务进行管理，包括服务注册与发现，服务治理，此处作为集群使用
    -- cloud-eureka-server7002：同上
    -- cloud-provider-payment8001：生产者，此处用于用作集群
    -- cloud-provider-payment8003：生产者，集群
    -- cloud-consumer-feign-order80 负载均衡消费者

