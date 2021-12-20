package com.pika.store.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.pika.store.Configuration.ViewConfiguration;
import com.pika.store.Models.User;
import com.pika.store.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @PostMapping("/registration")
    public Mono<ResponseEntity<User>> registration(@RequestBody User user){
        return registrationService.registrationUser(user)
                .map(ResponseEntity::ok);
    }
}
