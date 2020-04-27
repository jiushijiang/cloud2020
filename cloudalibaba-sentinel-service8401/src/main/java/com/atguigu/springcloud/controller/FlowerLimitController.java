package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowerLimitController {

    @GetMapping("/testA")
    public String testA(){

        return "-------------testA";//调试线程数
    }

    @GetMapping("/testB")
    public String testB(){

        try {
            Thread.sleep(2000);//调试线程数(1.休眠线程
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "-------------testB";
    }

    /**
     * 关联流控：A接口关联流控B，那么A访问多了，B就会出现报错情况（支付流控下单，下单报错）
     * @return
     */
    @GetMapping("/testC")
    public String testC(){

        return "-------------testC";
    }

    /**
     * 流控效果--预热（Warm Up ）：初始QPS=QPS%3（冷却数），从初始QPS根据冷却时间逐渐预热到最终的QPS
     * 只能是QPS
     * @return
     */
    @GetMapping("/testD")
    public String testD(){

        return "-------------testD";
    }
    /**
     * 流控效果--预热（Warm Up ）：初始QPS=QPS%3（冷却数），从初始QPS根据冷却时间逐渐预热到最终的QPS
     * 只能是QPS
     * @return
     */
    @GetMapping("/testE")
    public String testE(){

        return "-------------testE";
    }

    /**
     * 测试熔断降级：RT
     * 条件：当请求数量大于5且平均响应时间超过阈值时触发
     * 结果：短路器断开
     * @return
     */
    @GetMapping("/testF")
    public String testF(){
        //沉睡1s钟
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "-------------testF";
    }

    /**
     * 测试熔断降级：异常比例数
     * 条件：当异常比例数大于阈值且请求数量大于5时发生降级
     * 结果：短路器断开
     * @return
     */
    @GetMapping("/testG")
    public String testG(){
        //报错
       int i=10/0;

        return "-------------testG";
    }

    /**
     * 测试熔断降级：异常数
     * 条件：当异常数大于阈值且请求数量大于5时发生降级
     * 结果：短路器断开
     * @return
     */
    @GetMapping("/testH")
    public String testH(){
        //报错
        int i=10/0;

        return "-------------testH";
    }

    /**
     * 测试热点key降级：当某个参数在时间窗口期且阈值达到设定的次数时会进行熔断限流
     * 结果：调用设置的熔断方法
     * 特例：可以对某个具体的热点参数值进行排除，排除之后，只有参数值达到新的阈值时才会进行
     * 调用设置的熔断方法
     * 注：方法内报错不会调用相对应的限流方法
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "error_testHotKey")//value可以为任何值，但值要唯一，blockHandler设置相对应得熔断方法
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){

        return "-------------testHotKey";
    }

    public String error_testHotKey(String p1 , String p2, BlockException b){

        return "-------------error_testHotKey";
    }
}
