package <%= packageName %>.security.controllers;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import <%= packageName %>.mapper.Converter;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.domain.entities.<%= entityName %>;
import <%= packageName %>.adapter.I<%= entityName %>RepositoryAdapter;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private I<%= entityName %>RepositoryAdapter userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody <%= entityName %> userModel, final HttpServletRequest request) {
        <%= entityName %> user = userService.registerUser((<%= entityName %>Mapper) Converter.toModel(userModel,<%= entityName %>Mapper.class));
       
        return ResponseEntity.ok().body("Success");
    }

}
