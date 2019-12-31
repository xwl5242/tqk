package com.quanchong.dataoke.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 自定义AutowireCapableBeanJobFactory
 * 和springboot官方的版本差异之处在createJobInstance方法中
 * 具体情况请查阅 createJobInstance 方法中的注释
 * 注：为了解决 Seata 的 GlobalTransactionScanner 类创建定时任务代理类时的 NPE(NullPointerException)
 */
@Configuration
public class MyAutowireCapableBeanJobFactory extends SpringBeanJobFactory {

    private final AutowireCapableBeanFactory beanFactory;

    public MyAutowireCapableBeanJobFactory(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        this.beanFactory.autowireBean(jobInstance);
        /**
         * springboot官方版本
         * this.beanFactory.initializeBean(jobInstance, (String)null);
         */
        this.beanFactory.initializeBean(jobInstance, jobInstance.getClass().getName());
        return jobInstance;
    }

}
