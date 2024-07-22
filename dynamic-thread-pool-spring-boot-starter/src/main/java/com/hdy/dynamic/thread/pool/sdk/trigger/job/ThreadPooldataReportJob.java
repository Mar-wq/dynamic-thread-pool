package com.hdy.dynamic.thread.pool.sdk.trigger.job;

import com.alibaba.fastjson.JSON;
import com.hdy.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import com.hdy.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.hdy.dynamic.thread.pool.sdk.registry.IRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author Hdy
 * @description 线程池数据上报任务
 * @date 2024/5/25
 */
public class ThreadPooldataReportJob {
    private final Logger logger = LoggerFactory.getLogger(ThreadPooldataReportJob.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;

    private final IRegistry regsitry;

    public ThreadPooldataReportJob(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry regsitry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.regsitry = regsitry;
    }

    @Scheduled(cron = "0/20 * * * * ?")
    public void execReportThreadPoolList() {
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        regsitry.reportThreadPool(threadPoolConfigEntities);
        logger.info("动态线程池，上报信息{}", JSON.toJSONString(threadPoolConfigEntities));

        for (ThreadPoolConfigEntity threadPoolConfigEntity : threadPoolConfigEntities) {
            regsitry.reportThreadPoolConfigParameter(threadPoolConfigEntity);
            logger.info("动态线程池， 上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
        }
    }
}
