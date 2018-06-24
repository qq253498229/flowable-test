package com.example.demo;

import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author wangbin
 */
@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      saveUser("worker1");
      saveUser("worker2");
      saveUser("leader1");
      saveUser("leader2");
      saveUser("manager1");
      saveUser("manager2");

      saveGroup("worker");
      saveGroup("leader");
      saveGroup("manager");

      saveRelation("worker1", "worker");
      saveRelation("worker2", "worker");
      saveRelation("leader1", "leader");
      saveRelation("leader2", "leader");
      saveRelation("manager1", "manager");
      saveRelation("manager2", "manager");
    };
  }

  private void saveRelation(String userId, String groupId) {
    Group group = identityService.createGroupQuery().groupMember(userId).singleResult();
    if (group == null) {
      identityService.createMembership(userId, groupId);
    }
  }

  @Resource
  private IdentityService identityService;

  private void saveUser(String userId) {
    User worker1 = identityService.createUserQuery().userId(userId).singleResult();
    if (worker1 == null) {
      User user = identityService.newUser(userId);
      user.setPassword(userId);
      identityService.saveUser(user);
    }
  }

  private void saveGroup(String groupId) {
    Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
    if (group == null) {
      group = identityService.newGroup(groupId);
      group.setName(groupId);
      identityService.saveGroup(group);
    }
  }
}
