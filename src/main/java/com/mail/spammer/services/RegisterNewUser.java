package com.mail.spammer.services;

import com.mail.spammer.entities.User;
import com.mail.spammer.repositories.UserRepositoryManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewUser implements UserDetailsManager {
    private final UserRepositoryManager userRepositoryManager;

    public RegisterNewUser(UserRepositoryManager userRepositoryManager) {
        this.userRepositoryManager = userRepositoryManager;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        System.out.println(userDetails.getUsername());
        userRepositoryManager.saveUser(new User(
                userDetails.getUsername(),
                userDetails.getPassword()
        ));
    }

    @Override
    public void updateUser(UserDetails userDetails) {

    }

    @Override
    public void deleteUser(String s) {

    }

    @Override
    public void changePassword(String s, String s1) {

    }

    @Override
    public boolean userExists(String s) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
