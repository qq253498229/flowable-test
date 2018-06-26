package com.example.demo.process;

import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Package com.example.demo.process
 * Module
 * Project FlowableTest
 * Email 253498229@qq.com
 * Created on 2018/6/23 下午10:44
 *
 * @author wangbin
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

  @Resource
  private RuntimeService runtimeService;
  @Resource
  private TaskService taskService;
  @Resource(name = "formServiceBean")
  private FormService formService;
  @Resource
  private HistoryService historyService;
  @Resource
  private RepositoryService repositoryService;

  /**
   * 实例列表
   */
  @GetMapping
  public ResponseEntity list() {
    List<Map<String, Object>> collect = repositoryService.createProcessDefinitionQuery().latestVersion().list()
            .stream().map(p -> {
              Map<String, Object> result = new HashMap<>();
              result.put("key", p.getKey());
              result.put("name", p.getName());
              return result;
            }).collect(Collectors.toList());
    return ok(collect);
  }

  /**
   * 开启实例，并将当前任务指向用户
   *
   * @param key    实例key
   * @param userId 开启实例的用户id
   */
  @PostMapping("/{key}/{userId}")
  public ResponseEntity create(@PathVariable("key") String key, @PathVariable("userId") String userId) {
    ProcessInstance process = runtimeService.startProcessInstanceByKey(key);
    Task task = taskService.createTaskQuery().processInstanceId(process.getId()).singleResult();
    taskService.claim(task.getId(), userId);
    return ok().build();
  }

}
