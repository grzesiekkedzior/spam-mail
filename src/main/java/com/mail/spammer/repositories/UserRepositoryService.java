package com.mail.spammer.repositories;

import com.mail.spammer.entities.User;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class UserRepositoryService {
    private final UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }
}
