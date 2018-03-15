package com.gs.schedules;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Administrator on 2018/3/14.
 */
public class BuyWorks {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("money","100");
        jobDataMap.put("string","breakFirst");
        //调度器
        JobDetail jobDetail = JobBuilder.newJob(BuyWorlk.class)
                .withIdentity("buyWork","group_1")
                .setJobData(jobDataMap).build();

        //任务
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("buyTrigger", "group_1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();


        //触发器
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
