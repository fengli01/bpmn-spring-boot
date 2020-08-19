package cn.zunyi001.flowable.service.impl;

import cn.zunyi001.flowable.base.service.impl.BaseServiceImpl;
import cn.zunyi001.flowable.mapper.LeaveRecordMapper;
import cn.zunyi001.flowable.model.LeaveRecord;
import cn.zunyi001.flowable.service.LeaveRecordService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: fengli
 * @date: 2020/8/19 9:46 上午
 */
@Service
public class LeaveRecordServiceImpl extends BaseServiceImpl<LeaveRecordMapper, LeaveRecord, Integer> implements LeaveRecordService {
}
