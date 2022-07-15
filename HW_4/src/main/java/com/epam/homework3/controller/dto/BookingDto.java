package com.epam.homework3.controller.dto;

import com.epam.homework3.controller.dto.group.OnCreate;
import com.epam.homework3.controller.validation.DateConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    @Null(message = "id must be empty on creation", groups = OnCreate.class)
    private Long id;

    @NotBlank(message = "dateIn can`t be empty", groups = OnCreate.class)
    @DateConstraint
    private LocalDate dateIn;

    @NotBlank(message = "dateOut can`t be empty", groups = OnCreate.class)
    @DateConstraint
    private LocalDate dateOut;

    @Null(message = "dateCreation must be empty on creation", groups = OnCreate.class)
    private LocalDate dateCreation;

    @NotBlank(message = "user id can`t be empty", groups = OnCreate.class)
    private Long userId;

    @NotBlank(message = "roomId can`t be empty", groups = OnCreate.class)
    private Long roomId;

    @NotBlank(message = "sum can`t be empty", groups = OnCreate.class)
    private Double sum;
}
