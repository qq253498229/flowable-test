package com.example.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wangbin
 */
@Service
@Slf4j
public class PassMailDelegate implements JavaDelegate {
    @Resource
    private ProcessEngine processEngine;

    @Override
    public void execute(DelegateExecution execution) {
        log.info("接到通过回调.");
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(execution.getProcessInstanceId()).singleResult();
        String startUserId = processInstance.getStartUserId();
        log.info("startUserId:{}", startUserId);
        Date startTime = processInstance.getStartTime();
        log.info("startTime:{}", startTime);
        log.info("instance id:{}", execution.getRootProcessInstanceId());
    }
}
