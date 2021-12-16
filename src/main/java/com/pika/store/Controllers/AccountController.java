package com.pika.store.Controllers;

import com.pika.store.Models.User;
import com.pika.store.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/account")
    public Mono<User> getAccount(Principal principal){
        return accountService.getUser(principal.getName());
    }

    @PatchMapping("/account")
    public Mono<User> editAccount(@RequestBody User editedUser, Principal principal){
        return accountService.editUser(principal.getName(), editedUser);
    }

}
