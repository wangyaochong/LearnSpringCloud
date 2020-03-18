package com.wangyaochong.service.impl;

import com.atguigu.springcloud.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wangyaochong.dao.PaymentDao;
import com.wangyaochong.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Payment)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 13:51:44
 */
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    /**
     * 具体的属性可以查看下面的类
     * {@link com.netflix.hystrix.HystrixCommandProperties}
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")


    })
    public String paymentCircuitBreaker(Integer i) {
        if (i < 0) {
            throw new RuntimeException("不能为负数");
        }
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "paymentCircuitBreaker " + i;
    }

    @Override
    public String paymentCircuitBreakerFallBack(Integer i) {
        return "paymentCircuitBreakerFallBack " + i;
    }

    @Override
    public String paymentInfoOk() {
        return "paymentInfoOk";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoBad_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public String paymentInfoBad() {
        try {
            //如果是运行异常，也会调用fallback方法
//            int i = 1 / 0;
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "paymentInfoBad";
    }

    public String paymentInfoBad_handler() {
        return "paymentInfoBad_handler";
    }

    @Resource
    private PaymentDao paymentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Long id) {
        return this.paymentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Payment> queryAllByLimit(int offset, int limit) {
        return this.paymentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Payment payment) {
        return this.paymentDao.insert(payment);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        this.paymentDao.update(payment);
        return this.queryById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.paymentDao.deleteById(id) > 0;
    }
}