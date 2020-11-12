package com.example.inflearnrestapi.common;

import com.example.inflearnrestapi.index.IndexController;
import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.Errors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class ErrorsModel extends RepresentationModel{

    private Errors errors;

    public ErrorsModel(Errors errors) {
        this.errors = errors;
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }

    public static ErrorsModel of(Errors errors){
        return new ErrorsModel(errors);
    }
}
