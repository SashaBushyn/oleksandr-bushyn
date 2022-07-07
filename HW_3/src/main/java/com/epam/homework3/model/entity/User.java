package com.epam.homework3.model.entity;

import com.epam.homework3.model.enams.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private Role role;
    private boolean blocked;

}
