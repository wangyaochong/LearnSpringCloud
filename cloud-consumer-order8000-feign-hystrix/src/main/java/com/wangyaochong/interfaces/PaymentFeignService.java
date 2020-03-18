package com.wangyaochong.interfaces;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangyaochong
 * @date 2020/3/15 19:56
 */
@FeignClient(value = "CLOUD-PAYMENT-SERVICE-HYSTRIX", fallback = PaymentFeignServiceFallBackImpl.class)
@Component
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/timeout")
    Integer paymentTimeout();


    @GetMapping(value = "/payment/hystrix/ok")
    public String ok();


    @GetMapping(value = "/payment/hystrix/bad")
    public String bad();


}
