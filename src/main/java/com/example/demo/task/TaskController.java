package com.example.demo.task;

import org.flowable.engine.ProcessEngine;
import org.flowable.form.api.FormInfo;
import org.flowable.form.api.FormModel;
import org.flowable.form.engine.FormEngine;
import org.flowable.task.api.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
    @Resource
    private FormEngine formEngine;

    @GetMapping("/{userId}")
    public ResponseEntity list(@PathVariable String userId) {
        List<Task> list = processEngine.getTaskService().createTaskQuery().taskCandidateOrAssigned(userId).list();
        List<CustomTask> collect = list.stream().map(CustomTask::new).collect(Collectors.toList());
        return ok(collect);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity delete(@PathVariable String taskId) {
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        processEngine.getRuntimeService().deleteProcessInstance(task.getProcessInstanceId(), null);
        return ok().build();
    }

    @GetMapping("/info/{taskId}")
    public ResponseEntity info(@PathVariable String taskId) {
        // 获取当前任务
        Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        // 获取流程开始的表单
        FormInfo startFormModel = processEngine.getRuntimeService().getStartFormModel(task.getProcessDefinitionId(), task.getProcessInstanceId());
        // 获取当前任务的表单
        FormModel formModel = formEngine.getFormRepositoryService().getFormModelByKey(task.getFormKey()).getFormModel();
        CustomTask customTask = new CustomTask(task, formModel, startFormModel.getFormModel());
        return ok(customTask);
    }

    @PostMapping("/{taskId}")
    public ResponseEntity complete(@PathVariable String taskId, @RequestBody Map variables) {
        processEngine.getTaskService().complete(taskId, variables);
        return ok().build();
    }
}
