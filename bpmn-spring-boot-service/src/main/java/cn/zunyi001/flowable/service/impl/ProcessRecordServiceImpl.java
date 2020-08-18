package cn.zunyi001.flowable.service.impl;

import cn.zunyi001.flowable.base.service.impl.BaseServiceImpl;
import cn.zunyi001.flowable.mapper.ProcessRecordMapper;
import cn.zunyi001.flowable.model.ProcessRecord;
import cn.zunyi001.flowable.service.ProcessRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/14 3:46 下午
 */
@Service
public class ProcessRecordServiceImpl extends BaseServiceImpl<ProcessRecordMapper, ProcessRecord, Integer> implements ProcessRecordService {

    @Resource
    private ProcessRecordMapper processRecordMapper;

}
