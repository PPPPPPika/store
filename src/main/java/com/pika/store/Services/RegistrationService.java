package com.pika.store.Services;

import com.pika.store.Models.Enums.SecurityEnums.Role;
import com.pika.store.Models.User;
import com.pika.store.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> registrationUser(User user){
        if (user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().isBlank() &&
                user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().isBlank()){
            user.setRole(Role.ROLE_USER);
            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            return userRepository.save(user);
        }
        else
            return Mono.empty();
    }



}