package com.example.inflearnrestapi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class EventModel extends EntityModel<Event> {

    public EventModel(Event event, Link...links){
        super(event, links);
    }
}
