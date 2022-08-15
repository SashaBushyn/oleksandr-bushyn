package com.epam.homework3.controller.dto;

import com.epam.homework3.controller.dto.group.OnCreate;
import com.epam.homework3.controller.dto.group.OnUpdate;
import com.epam.homework3.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {
    @Null(message = "id must be empty on create", groups = OnCreate.class)
    private Long id;

    @NotBlank(message = "FirstName shouldn` t be empty")
    private String firstName;

    @NotBlank(message = "LastName shouldn` t be empty")
    private String lastName;

    @NotBlank(message = "Login shouldn` t be empty", groups = OnCreate.class)
    @Null(message = "login must be empty", groups = OnUpdate.class)
    private String login;

    @Email(message = "{email.validation}")
    @NotBlank(message = "{email.notEmpty}", groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private String email;

    @NotBlank(message = "Password shouldn` t be empty", groups = OnCreate.class)
    @Null(groups = OnUpdate.class)
    private String password;

    private Role role;

    private boolean blocked;
}
