package com.wangyaochong.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.wangyaochong.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Payment)表控制层
 *
 * @author makejava
 * @since 2020-03-14 11:11:26
 */
@RestController
@Slf4j
public class PaymentController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Payment selectOne(Long id) {
        return this.paymentService.queryById(id);
    }

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        log.info("插入ss结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功", result);
        } else {
            return new CommonResult<>(200, "插入数据失败", result);
        }
    }

    @GetMapping(value = "/payment/timeout")
    public Integer paymentTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return 1;
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        log.info("插入结果：" + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询数据库成功", payment);
        } else {
            return new CommonResult<>(200, "查询数据失败");
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("--->" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }


    @GetMapping(value = "/payment/hystrix/ok")
    public String ok() {
        return paymentService.paymentInfoOk();
    }

    @GetMapping(value = "/payment/hystrix/bad")
    public String bad() {
        return paymentService.paymentInfoBad();
    }
}
