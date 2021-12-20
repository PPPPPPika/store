package com.pika.store.Controllers;

import com.pika.store.Security.JWT.JwtUtil;
import com.pika.store.Models.User;
import com.pika.store.Security.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthenticationRequest {
        private String email;
        private String password;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(@RequestBody AuthenticationRequest request) {
        return Mono.just(request).flatMap(value ->
                userService.findByUsername(value.getEmail())
                        .cast(User.class)
                        .map(userDetails ->{
                            if (new BCryptPasswordEncoder(12).matches(value.getPassword(), userDetails.getPassword()))
                                return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
                            else
                                return UNAUTHORIZED;
                            })
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }

}
