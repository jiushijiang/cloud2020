package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //1.新建订单
        log.info("----开始调用订单服务");
        orderDao.create(order);
        //2.扣减库存
        log.info("-------商品微服务扣减库存");
        storageService.decrease(order.getProductId(),order.getCount());
        //3.扣减账户
        log.info("-----账户微服务扣除账户余额");
        accountService.decrease(order.getUserId(),order.getMoney());

        //4.修改订单状态
        log.info("--------修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("------所有服务均调用完成，☺");


        //......

    }
}
