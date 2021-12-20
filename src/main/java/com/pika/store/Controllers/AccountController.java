package com.pika.store.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.pika.store.Configuration.ViewConfiguration;
import com.pika.store.Models.User;
import com.pika.store.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/shop")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @GetMapping("/account")
    public Mono<ResponseEntity<User>> getAccount(Principal principal){
        return accountService.getUser(principal.getName())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @PatchMapping("/account")
    public Mono<ResponseEntity<User>> editAccount(@RequestBody User editedUser, Principal principal){
        return accountService.editUser(principal.getName(), editedUser)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
