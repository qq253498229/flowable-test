package com.example.demo.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.idm.api.User;
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
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Resource
    private ProcessEngine processEngine;

    @ApiOperation("获取用户列表")
    @GetMapping
    public ResponseEntity users() {
        IdentityService identityService = this.processEngine.getIdentityService();
        List<User> list = identityService.createUserQuery().list();
        return ok(list);
    }
}
