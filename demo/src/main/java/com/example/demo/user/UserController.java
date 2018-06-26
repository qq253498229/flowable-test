package com.example.demo.user;

import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author wangbin
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Resource
  private IdentityService identityService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Map param) {
    String userId = (String) param.get("userId");
    String password = (String) param.get("password");
    boolean b = identityService.checkPassword(userId, password);
    return ok(b);
  }

  @PostMapping
  public ResponseEntity insertUser(@RequestBody Map map) {
    User user = identityService.newUser((String) map.get("id"));
    saveUser(map, user);
    return ok(true);
  }

  @PutMapping
  public ResponseEntity updateUser(@RequestBody Map map) {
    User user = identityService.createUserQuery().userId((String) map.get("id")).singleResult();
    saveUser(map, user);
    return ok(true);
  }

  @GetMapping("/{userId}")
  public ResponseEntity getUserGroup(@PathVariable("userId") String userId) {
    List<Group> groups = identityService.createGroupQuery().groupMember(userId).list();
    List<Map<String, Object>> result = new ArrayList<>();
    for (Group group : groups) {
      Map<String, Object> map = new HashMap<>();
      map.put("groupId", group.getId());
      map.put("groupName", group.getName());
      result.add(map);
    }
    return ok(result);
  }

  private void saveUser(@RequestBody Map map, User user) {
    user.setFirstName((String) map.get("firstName"));
    user.setLastName((String) map.get("lastName"));
    user.setEmail((String) map.get("email"));
    user.setPassword((String) map.get("password"));
    identityService.saveUser(user);
  }
}
