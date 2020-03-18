package com.wangyaochong.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wangyaochong.interfaces.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfoBadGlobalHandler")
public class OrderController {

    @Resource
    RestTemplate restTemplate;
    @Resource
    PaymentFeignService paymentFeignService;

    //    String PAYMENT_URL = "http://localhost:8001";
    String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public Integer timeout() {
        return paymentFeignService.paymentTimeout();
    }

    @GetMapping("/consumer/paymentEntity/create")
    public CommonResult createEntity(Payment payment) {
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("/consumer/paymentEntity/get/{id}")
    public CommonResult getPaymentEntity(@PathVariable("id") Long id) {
        return restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class).getBody();
    }

    @GetMapping(value = "/payment/hystrix/ok")
    public String ok() {
        log.info("/payment/hystrix/ok  called");
        return paymentFeignService.ok();
    }


    //    @HystrixCommand(fallbackMethod = "paymentInfoBad_handler"
//            , commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")}
//    )
    @HystrixCommand
    @GetMapping(value = "/payment/hystrix/bad")
    public String bad() {
        log.info("/payment/hystrix/bad  called");
        return paymentFeignService.bad();
    }

    public String paymentInfoBad_handler() {
        System.out.println("hello");
        return "paymentInfoBad_handler order";
    }

    public String paymentInfoBadGlobalHandler() {
        return "paymentInfoBadGlobalHandler";
    }


}
