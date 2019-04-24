package com.example.demo.task;

import lombok.Data;
import org.flowable.task.api.Task;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Map;

/**
 * @author wangbin
 */
@Entity
@Data
public class CustomTask {
    @Id
    private String id;
    private String name;
    private String assignee;
    private String formKey;
    private String description;
    private String owner;
    private Date createTime;
    private Date claimTime;
    private Date dueDate;
    private String processDefinitionId;
    private String processInstanceId;
    @Transient
    private Map<String, Object> processVariables;

    public CustomTask(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.assignee = task.getAssignee();
        this.formKey = task.getFormKey();
        this.description = task.getDescription();
        this.owner = task.getOwner();
        this.createTime = task.getCreateTime();
        this.claimTime = task.getClaimTime();
        this.dueDate = task.getDueDate();
        this.processDefinitionId = task.getProcessDefinitionId();
        this.processInstanceId = task.getProcessInstanceId();
        this.processVariables = task.getProcessVariables();
    }

}
