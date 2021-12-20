package com.pika.store.Services;

import com.pika.store.Models.User;
import com.pika.store.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    private final UserRepository userRepository;

    @Autowired
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> getUser(String email){
        return userRepository.findByEmail(email);
    }

    public Mono<User> editUser(String email, User editedUser){
        return userRepository.findByEmail(email).map(oldUser -> processingUser(oldUser, editedUser)).flatMap(userRepository::save);
    }

    private User processingUser(User oldUser, User newUser){
        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty() && !newUser.getEmail().isBlank()){
            if (!oldUser.getEmail().equals(newUser.getEmail()))
                oldUser.setEmail(newUser.getEmail());
        }
        if (newUser.getName() != null && !newUser.getName().isEmpty() && !newUser.getName().isBlank()){
            if (oldUser.getName() == null)
                oldUser.setName(newUser.getName());
            if (!oldUser.getName().equals(newUser.getName()))
                oldUser.setName(newUser.getName());
        }
        if (newUser.getNumber() != null && !newUser.getNumber().isEmpty() && !newUser.getNumber().isBlank()){
            if (oldUser.getNumber() == null)
                oldUser.setNumber(newUser.getNumber());
            if (!oldUser.getNumber().equals(newUser.getNumber()))
                oldUser.setNumber(newUser.getNumber());
        }
        if (newUser.getAddress() != null && !newUser.getAddress().isEmpty() && !newUser.getAddress().isBlank()){
            if (oldUser.getAddress() == null)
                oldUser.setAddress(newUser.getAddress());
            if (!oldUser.getAddress().equals(newUser.getAddress()))
                oldUser.setAddress(newUser.getAddress());
        }
        return oldUser;
    }
}
