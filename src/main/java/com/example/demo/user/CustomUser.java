package com.example.demo.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author wangbin
 */
@Entity
@Data
public class CustomUser {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
