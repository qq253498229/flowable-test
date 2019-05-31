package com.example.demo.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/test")
@Api(tags = {"测试流程"})
public class TestController {
    @Resource
    private ProcessEngine processEngine;

    @ApiOperation("用户列表")
    @GetMapping("/users")
    public ResponseEntity users() {
        IdentityService identityService = processEngine.getIdentityService();
        List<User> list = identityService.createUserQuery().list();
        return ok(list);
    }

    @ApiOperation("分组列表")
    @GetMapping("/groups")
    public ResponseEntity groups() {
        IdentityService identityService = processEngine.getIdentityService();
        List<Group> list = identityService.createGroupQuery().list();
        return ok(list);
    }


    @ApiOperation("开启流程")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "admin")
    @GetMapping("/start/{userId}")
    public ResponseEntity start(@PathVariable String userId) {
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("initiator", userId);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("test_process", variables);
        System.out.println(processInstance);
        return ok().build();
    }

    @ApiOperation("查看任务列表")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "admin")
    @GetMapping("/tasks/{userId}")
    public ResponseEntity tasks(@PathVariable String userId) {
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskCandidateOrAssigned(userId).list();
        List<Object> collect = list.stream().map(s -> {
            Map<String, Object> persistentState = (HashMap) ((TaskEntityImpl) s).getPersistentState();
            persistentState.put("taskId", s.getId());
            return persistentState;
        }).collect(Collectors.toList());
        return ok(collect);
    }

    @ApiOperation("完成任务")
    @ApiImplicitParam(name = "taskId", value = "任务id", required = true)
    @GetMapping("/done/{taskId}")
    public ResponseEntity done(@PathVariable String taskId) {
        processEngine.getTaskService().complete(taskId);
        return ok().build();
    }
}
