package com.pika.store.Controllers;

import com.pika.store.Security.AuthenticationManager;
import com.pika.store.Security.JWT.JwtUtil;
import com.pika.store.Models.User;
import com.pika.store.Security.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
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
        return Mono.just(request).flatMap(credentials ->
                userService.findByUsername(credentials.getEmail())
                        .cast(User.class)
                        .map(userDetails ->{
                            if (new BCryptPasswordEncoder(12).matches(credentials.getPassword(), userDetails.getPassword()))
                                return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
                            else
                                return UNAUTHORIZED;
                            })
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }
}
