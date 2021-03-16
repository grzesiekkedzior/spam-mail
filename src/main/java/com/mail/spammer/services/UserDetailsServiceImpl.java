package com.mail.spammer.services;

import com.mail.spammer.entities.User;
import com.mail.spammer.repositories.UserRepositoryService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepositoryService userRepositoryService;

    public UserDetailsServiceImpl(UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepositoryService.findByUserName(username);
        User usr = user.orElseThrow(() -> new UsernameNotFoundException("Check!!!"));
        return new SecurityUser(usr);
    }
}
