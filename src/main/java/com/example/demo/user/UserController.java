package com.example.demo.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.idm.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    public ResponseEntity user(@PathVariable String id) {
        IdentityService identityService = this.processEngine.getIdentityService();
        User user = identityService.createUserQuery().userId(id).singleResult();
        return ok(user);
    }

    @ApiOperation("保存用户")
    @PostMapping
    public ResponseEntity save(@RequestBody CustomUser dto) {
        IdentityService identityService = this.processEngine.getIdentityService();
        User user = identityService.createUserQuery().userId(dto.getId()).singleResult();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        identityService.saveUser(user);
        return ok().build();
    }
}
