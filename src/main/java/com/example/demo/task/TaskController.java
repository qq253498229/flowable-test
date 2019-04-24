package com.example.demo.task;

import org.flowable.engine.ProcessEngine;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private ProcessEngine processEngine;

    @GetMapping("/{userId}")
    public ResponseEntity list(@PathVariable String userId) {
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskCandidateOrAssigned(userId).list();
        List<CustomTask> collect = list.stream().map(CustomTask::new).collect(Collectors.toList());
        return ok(collect);
    }
}
