package com.quanchong.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quanchong.common.entity.SysJob;
import com.quanchong.common.util.DateUtils;
import com.quanchong.server.mapper.SysJobMapper;
import com.quanchong.server.service.SysJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 系统定时任务service
 * 系统定时任务在mysql数据库中配置
 */
@Service
@Transactional
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    private static final Logger logger = LoggerFactory.getLogger(SysJobServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper sysJobMapper;

    @Autowired
    private SysJobService sysJobService;

    /**
     * 项目重启后，初始化原本已经运行的定时任务
     */
    @PostConstruct
    private void initSchedule(){
        logger.info("定时任务初始化运行，即将运行系统中已有的定时任务");
        // 查询出所有可用定时任务
        List<SysJob> jobs = sysJobService.list().stream().
                filter(job -> job.getStatus().equals("0")).collect(Collectors.toList());
        // 遍历启动定时任务
        jobs.stream().forEach(job -> {
            try{
                add(scheduler, job.getGroupName(), job.getJobName(), job.getCron(), job.getClassName());
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        });

    }

    /**
     * 启动定时任务
     * @param job
     * @throws RuntimeException
     */
    @Override
    public int addSchedule(SysJob job) throws RuntimeException{
        int start = 0;
        if (StringUtils.isEmpty(job.getGroupName()) || StringUtils.isEmpty(job.getJobName())
                || StringUtils.isEmpty(job.getCron()) || StringUtils.isEmpty(job.getClassName())){
            throw new RuntimeException("group_name, job_name , cron, job_classname参数不能为空！");
        }
        // 构建查询条件
        QueryWrapper<SysJob> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name", job.getGroupName())
                .eq("job_name", job.getJobName()).eq("status", "0");
        // 查询指定的定时任务
        List<SysJob> jobList = sysJobMapper.selectList(wrapper);
        // 判断要启动的定时任务是否已经存在
        if(!jobList.isEmpty()){
            throw new RuntimeException("改job已经启动了...");
        }
        try{
            add(scheduler, job.getGroupName(), job.getJobName(), job.getCron(), job.getClassName());
            job.setStatus("0");
            job.setCreateTime(DateUtils.now());
            job.setUpdateTime(DateUtils.now());
            start = sysJobMapper.insert(job);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return start;
    }

    /**
     * 执行一次
     * @param job
     * @return
     * @throws RuntimeException
     */
    @Override
    public int runOnceSechdule(SysJob job, boolean isPersistence) throws RuntimeException {
        if (StringUtils.isEmpty(job.getJobName()) || StringUtils.isEmpty(job.getClassName())){
            throw new RuntimeException("job_name,job_classname参数不能为空！");
        }
        int runOnce = isPersistence?0:1;
        try {
            runOnce(scheduler, job.getJobName(), job.getClassName());
            if(isPersistence){
                job.setGroupName("temp");
                job.setStatus("-1");
                job.setCreateTime(DateUtils.now());
                job.setUpdateTime(DateUtils.now());
                runOnce = sysJobMapper.insert(job);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return runOnce;
    }

    /**
     * 添加并立即运行
     * @param job
     * @return
     * @throws RuntimeException
     */
    @Override
    public boolean addAndRunNowSchedule(SysJob job) throws RuntimeException {
        if (StringUtils.isEmpty(job.getGroupName()) || StringUtils.isEmpty(job.getJobName())
                || StringUtils.isEmpty(job.getCron()) || StringUtils.isEmpty(job.getClassName())){
            throw new RuntimeException("group_name, job_name , cron, job_classname参数不能为空！");
        }
        int success = 0;
        try{
            success += sysJobService.addSchedule(job);
            success += sysJobService.runOnceSechdule(job, true);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(2!=success){
            throw new RuntimeException("任务添加和启动失败...");
        }
        return success == 2;
    }

    /**
     * 更新定时任务的 Cron
     * @param job
     * @throws RuntimeException
     */
    @Override
    public int updateScheduleCron(SysJob job) throws RuntimeException{
        int update = 0;
        if(ObjectUtils.isEmpty(job.getId()) || ObjectUtils.isEmpty(job.getCron())){
            throw new RuntimeException("请设置要更新的任务！");
        }
        try {
            // 查询出指定id的定时任务
            SysJob sysJob = sysJobService.selectByIdAndStatus(job.getId(), "0");
            if(Optional.ofNullable(sysJob).isPresent()){
                // 获取定时任务初始Cron
                TriggerKey triggerKey = new TriggerKey(sysJob.getJobName(), sysJob.getGroupName());
                CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                String oldCronString = cronTrigger.getCronExpression();
                // 如果新的Cron和初始的不一致，执行更新Cron相关操作
                if(!oldCronString.equals(job.getCron())){
                    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
                    CronTrigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity(sysJob.getJobName(), sysJob.getGroupName())
                            .withSchedule(cronScheduleBuilder).build();
                    // 更新定时任务的CronTrigger
                    scheduler.rescheduleJob(triggerKey, trigger);
                    // 更新系统数据库中定时任务的Cron
                    sysJob.setCron(job.getCron());
                    sysJob.setUpdateTime(DateUtils.now());
                    update = sysJobMapper.updateById(sysJob);
                }
            }else{
                throw new RuntimeException("要操作的定时任务不存在");
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return update;
    }

    /**
     * 暂停定时任务
     * @param job 定时任务，只匹配定时任务id
     * @throws RuntimeException
     */
    @Override
    public int pauseSchedule(SysJob job) throws RuntimeException{
        int pause = 0;
        try{
            // 查询指定id的正在运行的定时任务
            SysJob sysJob = sysJobService.selectByIdAndStatus(job.getId(), "0");
            // 定时任务存在且处于运行状态
            if(Optional.ofNullable(sysJob).isPresent()){
                // 暂停定时任务
                pause(scheduler, sysJob.getJobName(), sysJob.getGroupName());
                // 更新定时任务为暂停状态
                sysJob.setStatus("2");
                sysJob.setUpdateTime(DateUtils.now());
                pause = sysJobMapper.updateById(sysJob);
            }else{
                throw new RuntimeException("要操作的定时任务不存在");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return pause;
    }

    /**
     * 恢复定时任务
     * @param job 定时任务，只匹配定时任务id
     * @throws RuntimeException
     */
    @Override
    public int resumeSchedule(SysJob job) throws RuntimeException{
        int resume = 0;
        try{
            // 查询指定id的已暂停的定时任务
            SysJob sysJob = sysJobService.selectByIdAndStatus(job.getId(), "2");
            // 定时任务存在且处于暂停状态
            if(Optional.ofNullable(sysJob).isPresent()){
                // 恢复运行状态
                resume(scheduler, sysJob.getJobName(), sysJob.getGroupName());
                // 更新定时任务为运行状态
                sysJob.setStatus("0");
                sysJob.setUpdateTime(DateUtils.now());
                resume = sysJobMapper.updateById(sysJob);
            }else{
                throw new RuntimeException("要操作的定时任务不存在");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return resume;
    }

    /**
     * 删除定时任务
     * @param job 定时任务，只匹配定时任务id
     * @throws RuntimeException
     */
    @Override
    public int deleteSchedule(SysJob job) throws RuntimeException{
        int delete = 0;
        try{
            // 查询指定id的正在运行的定时任务
            SysJob sysJob = sysJobService.selectByIdAndStatus(job.getId(), "0");
            // 定时任务存在且处于运行状态
            if(Optional.ofNullable(sysJob).isPresent()){
                // 删除定时任务
                delete(scheduler, sysJob.getJobName(), sysJob.getGroupName());
                // 更新定时任务为删除状态
                sysJob.setStatus("1");
                sysJob.setUpdateTime(DateUtils.now());
                delete = sysJobMapper.updateById(sysJob);
            }else{
                throw new RuntimeException("要操作的定时任务不存在");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return delete;
    }

}
