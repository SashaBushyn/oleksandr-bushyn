package com.epam.homework3.model.entity;

import com.epam.homework3.model.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private Role role;
    private boolean blocked;
}
