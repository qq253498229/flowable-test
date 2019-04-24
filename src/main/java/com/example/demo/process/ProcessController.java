package com.example.demo.process;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.form.api.FormModel;
import org.flowable.form.engine.FormEngine;
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
@RequestMapping("/process")
public class ProcessController {
    private static final String APP_KEY = "test-process";
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private FormEngine formEngine;

    /**
     * 获取流程定义列表
     */
    @GetMapping
    public ResponseEntity list() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentKey(APP_KEY).latest().singleResult();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).latestVersion().list();
        List<CustomProcessDefinition> collect = list.stream().map(CustomProcessDefinition::new).collect(Collectors.toList());
        return ok(collect);
    }

    @GetMapping("/{processDefinitionId}")
    public ResponseEntity info(@PathVariable String processDefinitionId) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        Map map = new HashMap<>();
        map.put("process", new CustomProcessDefinition(processDefinition));
        if (processDefinition.hasStartFormKey()) {
            FormModel formModel = formEngine.getFormRepositoryService().getFormModelByKey("leave-form").getFormModel();
            map.put("form", formModel);
        }
        return ok(map);
    }

    @GetMapping("/{userId}")
    public ResponseEntity start(@PathVariable String userId) {

        return ok().build();
    }
}
