package com.mail.spammer.services;

import com.mail.spammer.entities.User;
import com.mail.spammer.repositories.UserRepositoryManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JpaUserService implements UserDetailsService {
    private final UserRepositoryManager userRepositoryManager;

    public JpaUserService(UserRepositoryManager userRepositoryManager) {
        this.userRepositoryManager = userRepositoryManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositoryManager.findByUserName(username);
        User usr = user.orElseThrow(() -> new UsernameNotFoundException("Check!!!"));
        return new SecurityUser(usr);
    }
}
