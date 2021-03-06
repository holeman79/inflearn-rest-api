package com.example.inflearnrestapi.events;


import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Inflearn Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        Event event = new Event();
        String name = "Event";
        event.setName(name);
        String spring = "Spring";
        event.setDescription(spring);

        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(spring);


    }

    @Test
    public void testFree(){
        //Given
        Event event = Event.builder()
                .basePrice(0)
                .maxPrice(0)
                .build();

        // When
        event.update();

        //Then
        assertThat(event.isFree()).isTrue();

        //Given
        event = Event.builder()
                .basePrice(100)
                .maxPrice(0)
                .build();

        // When
        event.update();

        //Then
        assertThat(event.isFree()).isFalse();

        //Given
        event = Event.builder()
                .basePrice(0)
                .maxPrice(100)
                .build();

        // When
        event.update();

        //Then
        assertThat(event.isFree()).isFalse();
    }

    @Test
    public void testOffline(){
        //Given
        Event event = Event.builder()
                .location("강남역 네이버 D2 스타텁 팩토리")
                .build();

        // When
        event.update();

        //Then
        assertThat(event.isOffline()).isTrue();

        //Given
        event = Event.builder()
                .build();

        // When
        event.update();

        //Then
        assertThat(event.isOffline()).isFalse();
    }
}