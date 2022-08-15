package com.epam.homework3.controller;

import com.epam.homework3.UserUtil;
import com.epam.homework3.controller.dto.UserDto;
import com.epam.homework3.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
//    @MockBean
//    private UserRepository userRepository;

    @Test
    void getUser() throws Exception {
        UserDto userDto = UserUtil.testUserDto(1);
        when(userService.getUser(userDto.getEmail())).thenReturn(userDto);
        mockMvc.perform(get("/api/v1/user/" + userDto.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void getAllUsers() throws Exception {
        Page<UserDto> userDtoPage = new PageImpl<>(Arrays.asList(UserUtil.testUserDto(1), UserUtil.testUserDto(2)));
        when(userService.listUsers(any(Pageable.class))).thenReturn(userDtoPage);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String liststr = new ObjectMapper().writeValueAsString(userDtoPage);
        assertEquals(liststr, actualJsonResponse);
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = UserUtil.testUserDto(1);
        userDto.setId(null);
        String userStr = new ObjectMapper().writeValueAsString(userDto);

        when(userService.createUser(userDto)).thenReturn(userDto);

        ResultActions mvcResult = mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userStr))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), userStr));
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto userDto = UserUtil.testUserDto(1);
        String userStr = new ObjectMapper().writeValueAsString(userDto);

        when(userService.updateUser(userDto.getEmail(), userDto)).thenReturn(userDto);

        mockMvc.perform(patch("/api/v1/user/" + userDto.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userStr))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(result.getResponse().getContentAsString(), userStr));
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(anyLong());
        mockMvc.perform(delete("/api/v1/user/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}