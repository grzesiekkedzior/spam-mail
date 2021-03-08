package com.mail.spammer.repositories;

import com.mail.spammer.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryManager {
    private final UserRepository userRepository;

    public UserRepositoryManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }
}
