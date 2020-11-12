package com.example.inflearnrestapi.events;

import com.example.inflearnrestapi.common.ErrorsModel;
import com.example.inflearnrestapi.index.IndexController;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final EventValidator eventValidator;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors){
        if(errors.hasErrors()){
            return badRequest(errors);
        }
        eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = eventRepository.save(event);

        WebMvcLinkBuilder selfLinkBuilder = getSelfLink(newEvent);
        URI createdUri = selfLinkBuilder.toUri();
        EntityModel<Event> eventModel = EntityModel.of(event);
        //EventModel eventModel = new EventModel(event);
        eventModel.add(linkTo(EventController.class).withRel("query-events"));
        eventModel.add(selfLinkBuilder.withSelfRel());
        eventModel.add(selfLinkBuilder.withRel("update-event"));
        eventModel.add(Link.of("/docs/index.html#resources-events-create").withRel("profile"));
        return ResponseEntity.created(createdUri).body(eventModel);
    }

    private WebMvcLinkBuilder getSelfLink(Event newEvent) {
        return linkTo(EventController.class).slash(newEvent.getId());
    }

    private ResponseEntity<ErrorsModel> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(ErrorsModel.of(errors));
    }

    @GetMapping
    public ResponseEntity queryEvents(Pageable pageable, PagedResourcesAssembler<Event> assembler){
        Page<Event> page = this.eventRepository.findAll(pageable);
        var pagedModel = assembler.toModel(page, e -> EntityModel.of(e)
                .add(getSelfLink(e).withSelfRel()))
                .add(Link.of("/docs/index.html#resources-events-list").withRel("profile"));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEvent(@PathVariable Integer id){
        Optional<Event> optionalEvent = this.eventRepository.findById(id);
        if(optionalEvent.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Event event = optionalEvent.get();
        EntityModel<Event> eventEntityModel = EntityModel.of(event)
                .add(getSelfLink(event).withSelfRel())
                .add(Link.of("/docs/index.html#resources-events-get").withRel("profile"));
        return ResponseEntity.ok(eventEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id,
                                      @RequestBody @Valid EventDto eventDto,
                                      Errors errors){
        Optional<Event> optionalEvent = this.eventRepository.findById(id);
        if(optionalEvent.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(errors.hasErrors()){
            return badRequest(errors);
        }

        this.eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()){
            return badRequest(errors);
        }

        Event existingEvent = optionalEvent.get();
        this.modelMapper.map(eventDto, existingEvent);
        this.eventRepository.save(existingEvent);

        EntityModel<Event> eventEntityModel = EntityModel.of(existingEvent)
                .add(getSelfLink(existingEvent).withSelfRel())
                .add(Link.of("/docs/index.html#resources-events-update").withRel("profile"));

        return ResponseEntity.ok(eventEntityModel);
    }
}
