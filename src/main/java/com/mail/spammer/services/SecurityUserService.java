package com.mail.spammer.services;

import com.mail.spammer.entities.User;
import com.mail.spammer.repositories.UserRepositoryService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsManager {
    private final UserRepositoryService userRepositoryService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityUserService(UserRepositoryService userRepositoryService,
                               UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        userRepositoryService.saveUser(new User(
                userDetails.getUsername(),
                passwordEncoder.encode(userDetails.getPassword())
        ));
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String username) {
        userRepositoryService.deleteUser(username);
    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepositoryService.findByUserName(username).isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsService.loadUserByUsername(username);
    }
}
