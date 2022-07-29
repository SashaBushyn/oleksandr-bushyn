package com.epam.homework3.controller.dto;

import com.epam.homework3.controller.dto.group.OnCreate;
import com.epam.homework3.model.enums.RoomClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class RoomDto {
    @Null(message = "id must be empty on creation", groups = OnCreate.class)
    private Long id;

    @NotNull
    @Min(value = 1)
    private Integer roomNumber;

    @NotNull
    private RoomClass roomClass;

    @NotNull
    private Integer personsNumber;

    @NotNull
    private Double price;

    @NotNull
    private boolean blocked;
}
