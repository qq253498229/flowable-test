package com.example.demo.project;

import org.flowable.engine.FormService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.example.demo.consts.FlowConsts.CREATE_PROJECT_KEY;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Package com.example.demo.project
 * Module
 * Project FlowableTest
 * Email 253498229@qq.com
 * Created on 2018/6/23 下午10:44
 *
 * @author wangbin
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

  @Resource
  private RuntimeService runtimeService;
  @Resource
  private TaskService taskService;
  @Resource(name = "formServiceBean")
  private FormService formService;


  @PostMapping("/create/{userId}")
  public ResponseEntity create(@PathVariable("userId") String userId) {
    ProcessInstance process = runtimeService.startProcessInstanceByKey(CREATE_PROJECT_KEY);
    Task task = taskService.createTaskQuery().processInstanceId(process.getId()).singleResult();
    taskService.claim(task.getId(), userId);
    return ok(true);
  }

  @GetMapping("/form/{taskId}")
  public ResponseEntity form(@PathVariable("taskId") String taskId) {
    return ok(formService.getTaskFormData(taskId).getFormProperties());
  }
}
