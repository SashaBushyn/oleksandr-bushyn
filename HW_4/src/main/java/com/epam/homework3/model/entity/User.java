package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.Role;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;
    private Role role;
    private boolean blocked;
}
