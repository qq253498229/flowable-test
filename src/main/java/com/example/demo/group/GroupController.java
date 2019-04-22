package com.example.demo.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.idm.api.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/group")
@Api(tags = "分组接口")
public class GroupController {
    @Resource
    private ProcessEngine processEngine;

    @ApiOperation("获取分组列表")
    @GetMapping
    public ResponseEntity groups() {
        IdentityService identityService = this.processEngine.getIdentityService();
        List<Group> list = identityService.createGroupQuery().list();
        return ok(list);
    }
}
