package com.example.inflearnrestapi.events;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Errors;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = eventRepository.save(event);
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createdUri).body(event);
    }
}