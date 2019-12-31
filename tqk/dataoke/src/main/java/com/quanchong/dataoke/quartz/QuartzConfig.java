package com.quanchong.dataoke.quartz;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 关于定时任务Quartz的配置
 * 主要为了使用自定义的AutowireCapableBeanJobFactory,为什么使用定义类，详情请看 MyAutowireCapableBeanJobFactory 类
 */
@Configuration
public class QuartzConfig {

    private MyAutowireCapableBeanJobFactory myAutowireCapableBeanJobFactory;

    public QuartzConfig(MyAutowireCapableBeanJobFactory myAutowireCapableBeanJobFactory){
        this.myAutowireCapableBeanJobFactory = myAutowireCapableBeanJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(myAutowireCapableBeanJobFactory);
        return schedulerFactoryBean;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler(){
        /*使用spring容器管理scheduler, 使用时直接 Autowire Scheduler*/
        return schedulerFactoryBean().getScheduler();
    }
}
