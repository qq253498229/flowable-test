package com.example.demo.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author wangbin
 */
@Component
@Slf4j
public class MailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        log.info("MailDelegate received.");
        log.info("instance id:{}", execution.getRootProcessInstanceId());
    }
}
