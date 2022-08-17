package com.epam.homework3.controller;

import com.epam.homework3.OfferUtil;
import com.epam.homework3.controller.dto.OfferDto;
import com.epam.homework3.model.enums.OfferStatus;
import com.epam.homework3.service.OfferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OfferController.class)
class OfferControllerTest {
    @MockBean
    private OfferService offerService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    @Test
    void createOfferTest() throws Exception {
        OfferDto offerDto = OfferUtil.testOfferDto(1);
        offerDto.setId(null);
        when(offerService.createOffer(offerDto)).thenReturn(offerDto);
        String offerJson = jsonFromObject(offerDto);
        mockMvc.perform(post("/api/v1/offer").contentType(MediaType.APPLICATION_JSON).content(offerJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(offerJson, result.getResponse().getContentAsString()));
    }

    @Test
    void getAllOfferTest() throws Exception {
        Page<OfferDto> offerDtoPage = new PageImpl<>(Arrays.asList(OfferUtil.testOfferDto(1),OfferUtil.testOfferDto(2)));
        when(offerService.getAllOffers(any(Pageable.class))).thenReturn(offerDtoPage);
        mockMvc.perform(get("/api/v1/offer/all").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(jsonFromObject(offerDtoPage),result.getResponse().getContentAsString()));
    }

    @Test
    void changeOfferStatusTest() throws Exception{
        OfferDto offerDto = OfferUtil.testOfferDto(1);
        offerDto.setOfferStatus(OfferStatus.ACCEPT);
        when(offerService.changeStatus(offerDto.getOrderId(),offerDto.getOfferStatus())).thenReturn(offerDto);
        String offerJson = jsonFromObject(offerDto);
        mockMvc.perform(patch("/api/v1/offer/"+offerDto.getOrderId() +"/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("status",OfferStatus.ACCEPT.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(offerJson, result.getResponse().getContentAsString()));
    }

    @Test
    void getUserOffersTest() throws Exception{
        Page<OfferDto> offerDtoPage = new PageImpl<>(Arrays.asList(OfferUtil.testOfferDto(1),OfferUtil.testOfferDto(2)));
        Pageable pageable = PageRequest.of(0, 20);
        when(offerService.getUserOffers(1L,pageable)).thenReturn(offerDtoPage);
        mockMvc.perform(get("/api/v1/offer/"+1L+"/user").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(jsonFromObject(offerDtoPage),result.getResponse().getContentAsString()));
    }

    @Test
    void getOfferById() throws Exception{
        OfferDto offerDto = OfferUtil.testOfferDto(1);
        when(offerService.getOfferById(anyLong())).thenReturn(offerDto);
        String offerJson = jsonFromObject(offerDto);
        mockMvc.perform(get("/api/v1/offer/" + anyLong()).contentType(MediaType.APPLICATION_JSON).content(offerJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals(offerJson, result.getResponse().getContentAsString()));
    }

    @Test
    void deleteOffer() throws Exception{
        doNothing().when(offerService).deleteOffer(anyLong());

        mockMvc.perform(delete("/api/v1/offer/" + anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    private String jsonFromObject(Object o) {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(o);
    }
}