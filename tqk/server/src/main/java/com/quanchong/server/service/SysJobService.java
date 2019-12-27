package com.quanchong.server.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.quanchong.common.entity.sys.SysJob;
import org.quartz.*;
import org.springframework.util.ObjectUtils;

import java.util.Date;

public interface SysJobService extends IService<SysJob> {

    /**
     * 开启定时任务
     * @param scheduler 定时任务调度者
     * @param jobName 定时任务名称
     * @param groupName 定时任务组名称
     * @param cron 定时任务cron配置
     * @param className 定时任务要执行的任务的classname
     * @throws Exception
     */
    default void add(Scheduler scheduler, String groupName,
                          String jobName, String cron, String className) throws Exception{
        Job job = (Job) Class.forName(className).newInstance();
        JobDetail jd = JobBuilder.newJob(job.getClass()).
                withIdentity(jobName, groupName).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        scheduler.scheduleJob(jd, cronTrigger);
        // 启动定时任务
        scheduler.start();
    }

    /**
     * 只运行一次
     * @param scheduler
     * @param jobName
     * @param className
     * @throws Exception
     */
    default void runOnce(Scheduler scheduler, String jobName, String className) throws Exception{
        Job job = (Job) Class.forName(className).newInstance();
        JobDetail jd = JobBuilder.newJob(job.getClass()).
                withIdentity(jobName, "temp").build();
        //重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(3).withRepeatCount(0);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, "temp")
                .startAt(new Date()).withSchedule(simpleScheduleBuilder).build();
        scheduler.scheduleJob(jd, trigger);
        scheduler.start();
    }

    /**
     * 暂停定时任务
     * @param scheduler 定时任务调度者
     * @param jobName 定时任务名称
     * @param groupName 定时任务组名称
     * @throws Exception
     */
    default void pause(Scheduler scheduler, String jobName, String groupName) throws Exception{
        JobKey key = new JobKey(jobName, groupName);
        JobDetail detail = scheduler.getJobDetail(key);
        if(null == detail){
            return;
        }
        scheduler.pauseJob(key);
    }

    /**
     * 恢复定时任务
     * @param scheduler 定时任务调度者
     * @param jobName 定时任务名称
     * @param groupName 定时任务组名称
     * @throws Exception
     */
    default void resume(Scheduler scheduler, String jobName, String groupName) throws Exception{
        JobKey key = new JobKey(jobName, groupName);
        JobDetail detail = scheduler.getJobDetail(key);
        if(null == detail){
            return;
        }
        scheduler.resumeJob(key);
    }

    /**
     * 删除定时任务
     * @param scheduler 定时任务调度者
     * @param jobName 定时任务名称
     * @param groupName 定时任务组名称
     * @throws Exception
     */
    default void delete(Scheduler scheduler, String jobName, String groupName) throws Exception{
        JobKey key = new JobKey(jobName, groupName);
        JobDetail detail = scheduler.getJobDetail(key);
        if(null == detail){
            return;
        }
        scheduler.deleteJob(key);
    }

    /**
     * 根据id和status查询定时任务
     * @param id 定时任务id
     * @param status 定时任务status
     * @return
     * @throws Exception
     */
    default SysJob selectByIdAndStatus(Integer id, String status) throws Exception{
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("请设置需要操作的定时任务的id");
        }
        // 构造查询条件
        QueryWrapper<SysJob> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("status", status);
        // 获取指定的定时任务
        return getOne(wrapper);
    }

    /**
     * 开启定时任务
     * @param job
     * @throws RuntimeException
     */
    int addSchedule(SysJob job) throws RuntimeException;

    /**
     * 更新定时任务的Cron
     * @param job
     * @throws RuntimeException
     */
    int updateScheduleCron(SysJob job) throws RuntimeException;

    /**
     * 暂停定时任务
     * @param job
     * @throws RuntimeException
     */
    int pauseSchedule(SysJob job) throws RuntimeException;

    /**
     * 恢复定时任务
     * @param job
     * @throws RuntimeException
     */
    int resumeSchedule(SysJob job) throws RuntimeException;

    /**
     * 删除定时任务
     * @param job
     * @throws RuntimeException
     */
    int deleteSchedule(SysJob job) throws RuntimeException;

    /**
     * 只运行一次
     * @param job
     * @return
     * @throws RuntimeException
     */
    int runOnceSechdule(SysJob job, boolean isPersistence) throws RuntimeException;

    /**
     * 添加并立即运行
     * @param job
     * @return
     * @throws RuntimeException
     */
    boolean addAndRunNowSchedule(SysJob job) throws RuntimeException;

}
