package com.pika.store.Controllers;

import com.pika.store.Security.JWT.JwtUtil;
import com.pika.store.Models.User;
import com.pika.store.Security.SecurityContextRepository;
import com.pika.store.Security.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.Duration;

@RestController
public class AuthenticationController {
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final SecurityContextRepository securityContextRepository;

    @Autowired
    public AuthenticationController(UserService userService, JwtUtil jwtUtil, SecurityContextRepository securityContextRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.securityContextRepository = securityContextRepository;
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
