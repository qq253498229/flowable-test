package com.example.demo.task;

import org.flowable.engine.*;
import org.flowable.engine.impl.form.EnumFormType;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.consts.FlowConsts.CREATE_PROJECT_KEY;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/task")
public class TaskController {
  @Resource
  private TaskService taskService;

  @Resource
  private RuntimeService runtimeService;

  @Resource(name = "formServiceBean")
  private FormService formService;

  @Resource
  private RepositoryService repositoryService;

  @Resource
  private IdentityService identityService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<Map<String, Object>>> list(@PathVariable("userId") String userId) {
    List<String> groupIds = identityService.createGroupQuery().groupMember(userId).list().stream().map(Group::getId).collect(Collectors.toList());
    List<Task> list = taskService.createTaskQuery().or().taskCandidateOrAssigned(userId).taskCandidateGroupIn(groupIds).endOr()
            .orderByTaskDueDate().asc()
            .orderByTaskCreateTime().desc()
            .list();
    List<Map<String, Object>> result = this.getMaps(list);
    return ok(result);
  }

  private List<Map<String, Object>> getMaps(List<Task> list) {
    List<Map<String, Object>> result = new ArrayList<>();
    for (Task task : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("id", task.getId());
      map.put("name", task.getName());
      map.put("claimTime", task.getClaimTime());
      map.put("createTime", task.getCreateTime());
      map.put("dueDate", task.getDueDate());
      map.put("assignee", task.getAssignee());
      result.add(map);
    }
    return result;
  }

  @PostMapping("/create/{userId}")
  public ResponseEntity<Map<String, Object>> create(@PathVariable("userId") String userId) {
    ProcessInstance process = runtimeService.startProcessInstanceByKey(CREATE_PROJECT_KEY);
    Task task = taskService.createTaskQuery().processInstanceId(process.getId()).singleResult();
    taskService.claim(task.getId(), userId);
    Map<String, Object> result = new HashMap<>();
    result.put("taskId", task.getId());
    return ok(result);
  }

  @GetMapping("/form/{taskId}")
  public ResponseEntity<List<Map<String, Object>>> form(@PathVariable("taskId") String taskId) {
    List<Map<String, Object>> resultList = formService.getTaskFormData(taskId).getFormProperties().stream().map(p -> {
      Map<String, Object> result = new HashMap<>();
      result.put("value", p.getValue());
      result.put("id", p.getId());
      result.put("name", p.getName());
      result.put("typeName", p.getType().getName());
      if (p.getType() instanceof EnumFormType) {
        Map<String, Object> values = (Map<String, Object>) p.getType().getInformation("values");
        List enumList = new ArrayList();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
          Map<String, Object> enumMap = new HashMap<>();
          enumMap.put("id", entry.getKey());
          enumMap.put("value", entry.getValue());
          enumList.add(enumMap);
        }
        result.put("enum", enumList);
      }
      return result;
    }).collect(Collectors.toList());
    return ok(resultList);
  }

  @PostMapping("/{taskId}")
  public ResponseEntity complete(@RequestBody Map<String, Object> param, @PathVariable("taskId") String taskId) {
    List<Map<String, Object>> paramList = (List<Map<String, Object>>) param.get("param");
    Map<String, Object> variables = new HashMap<>();
    for (Map<String, Object> map : paramList) {
      if (!ObjectUtils.isEmpty(map.get("value"))) {
        variables.put((String) map.get("id"), map.get("value"));
      }
    }
    taskService.complete(taskId, variables);
    return ok().build();
  }

  @DeleteMapping("/{taskId}")
  public ResponseEntity delete(@PathVariable("taskId") String taskId, @RequestBody Map<String, Object> param) {
    String deleteReason = (String) param.get("deleteReason");
    taskService.deleteTask(taskId, deleteReason);
    return ok().build();
  }

  @PostMapping("/claim/{taskId}/{userId}")
  public ResponseEntity claim(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId) {
    taskService.claim(taskId, userId);
    return ok().build();
  }

  @PostMapping("/unClaim/{taskId}")
  public ResponseEntity unClaim(@PathVariable("taskId") String taskId) {
    taskService.unclaim(taskId);
    return ok().build();
  }
}
