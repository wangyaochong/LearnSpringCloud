package com.wangyaochong.interfaces;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wangyaochong
 * @date 2020/3/15 23:20
 */
@Component
@Slf4j
public class PaymentFeignServiceFallBackImpl implements PaymentFeignService {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        log.info("fallback getPaymentById");
        return null;
    }

    @Override
    public Integer paymentTimeout() {
        log.info("fallback paymentTimeout");

        return null;
    }

    @Override
    public String ok() {
        log.info("fallback ok");
        return "fallback ok";
    }

    @Override
    public String bad() {
        log.info("fallback bad");
        return "fallback bad";
    }


}
