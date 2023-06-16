package <%= packageName %>.application.adapters.controllers;


import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import <%= packageName %>.domain.<%=entityName%>;
import <%= packageName %>.domain.ports.interfaces.<%= entityName %>ServicePort;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    
    private <%= entityName %>ServicePort servicePort;

    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody <%= entityName %> userModel, final HttpServletRequest request) {
        servicePort.registerUser(userModel);
       
        return ResponseEntity.ok().body("Success");
    }

}
