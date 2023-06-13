package <%= packageName %>.application.adapters.controllers;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import <%= packageName %>.domain.ports.interfaces.<%= entityName %>ServicePort;

import <%= packageName %>.application.adapters.mapper.<%= entityName %>Mapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private <%= entityName %>ServicePort servicePort;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody <%= entityName %>Entity userModel, final HttpServletRequest request) {
        <%= entityName %>Entity user = servicePort.registerUser((<%= entityName %>Mapper) Converter.toModel(userModel,<%= entityName %>Mapper.class));
       
        return ResponseEntity.ok().body("Success");
    }

}
