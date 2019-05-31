package com.example.demo.process;

import lombok.Data;
import org.flowable.engine.repository.ProcessDefinition;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author wangbin
 */
@Entity
@Data
public class CustomProcessDefinition {
    @Id
    private String id;
    private String name;
    private String key;
    private Integer version;
    private String deploymentId;
    private Boolean hasStartFormKey;

    public CustomProcessDefinition(ProcessDefinition processDefinition) {
        this.id = processDefinition.getId();
        this.name = processDefinition.getName();
        this.key = processDefinition.getKey();
        this.version = processDefinition.getVersion();
        this.deploymentId = processDefinition.getDeploymentId();
        this.hasStartFormKey = processDefinition.hasStartFormKey();
    }
}
