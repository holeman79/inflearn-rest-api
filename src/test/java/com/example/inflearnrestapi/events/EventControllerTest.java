package com.example.inflearnrestapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
//@WebMvcTest
public class EventControllerTest {

//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    EventRepository eventRepository;

    @Test
    public void createEvent() {
//        EventDto eventDto = EventDto.builder()
//                .name("Spring")
//                .description("REST API Development with Spring")
//                .beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
//                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
//                .beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
//                .endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
//                .basePrice(100)
//                .maxPrice(200)
//                .limitOfEnrollment(100)
//                .location("강남역 D2 스타텁 팩토리")
//                .build();
//
//        Event event = Event.builder()
//                //.id(100)
//                .name("spring")
//                .description("REST API Development with Spring")
//                .beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
//                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
//                .beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
//                .endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
//                .basePrice(100)
//                .maxPrice(200)
//                .limitOfEnrollment(100)
//                .location("강남역 D2 스타텁 팩토리")
//                //.free(true)
//                //.offline(false)
//                .build();
//
//        Event event1 = modelMapper.map(eventDto, Event.class);
//
//        when(eventRepository.save(event1)).thenReturn(event);

//        mockMvc.perform(post("/api/events/")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaTypes.HAL_JSON)
//                    .content(objectMapper.writeValueAsString(eventDto)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("id").exists())
//                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
////                .andExpect(jsonPath("id").value(Matchers.not(100)))
//                .andExpect(jsonPath("id").value(Matchers.equalTo(100)))
//                .andExpect(jsonPath("free").value(Matchers.not(true)))
//        ;
    }
}
