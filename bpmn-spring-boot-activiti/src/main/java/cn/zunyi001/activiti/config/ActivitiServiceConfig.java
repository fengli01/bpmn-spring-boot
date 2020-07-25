package cn.zunyi001.activiti.config;

import org.activiti.engine.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/7/24 2:00 下午
 */
@Configuration
public class ActivitiServiceConfig {

    @Resource
    private ProcessEngine processEngine;

    @Bean
    @ConditionalOnMissingBean
    public RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    @Bean
    @ConditionalOnMissingBean
    public HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }

    @Bean
    @ConditionalOnMissingBean
    public RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }

    @Bean
    @ConditionalOnMissingBean
    public TaskService getTaskService() {
        return processEngine.getTaskService();
    }
}
