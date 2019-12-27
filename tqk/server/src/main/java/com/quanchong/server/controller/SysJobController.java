package com.quanchong.server.controller;

import com.quanchong.common.entity.sys.SysJob;
import com.quanchong.server.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/job")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    /**
     * 新建和启动定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/add")
    public String addJob(@RequestBody SysJob sysJob){
        int start = sysJobService.addSchedule(sysJob);
        return start==1?"success":"fail";
    }

    /**
     * 运行一次定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/runOnce")
    public String runOnceJob(@RequestBody SysJob sysJob){
        int runOnce = sysJobService.runOnceSechdule(sysJob, false);
        return runOnce==1?"success":"fail";
    }

    /**
     * 添加并立即运行一次定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/addAndRun")
    public String addAndRunJob(@RequestBody SysJob sysJob){
        boolean result = sysJobService.addAndRunNowSchedule(sysJob);
        return result?"success":"fail";
    }

    /**
     * 暂停定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/pause")
    public String pauseJob(@RequestBody SysJob sysJob){
        int pause = sysJobService.pauseSchedule(sysJob);
        return pause==1?"success":"fail";
    }

    /**
     * 恢复定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/resume")
    public String resumeJob(@RequestBody SysJob sysJob){
        int resume = sysJobService.resumeSchedule(sysJob);
        return resume==1?"success":"fail";
    }

    /**
     * 删除定时任务
     * @param sysJob
     * @return
     */
    @PostMapping("/remove")
    public String removeJob(@RequestBody SysJob sysJob){
        int remove = sysJobService.deleteSchedule(sysJob);
        return remove==1?"success":"fail";
    }

    /**
     * 更新定时任务Cron
     * @param sysJob
     * @return
     */
    @PostMapping("/updateJobCron")
    public String updateJobCron(@RequestBody SysJob sysJob){
        int update = sysJobService.updateScheduleCron(sysJob);
        return update==1?"success":"fail";
    }

}
