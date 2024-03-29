package com.mail.spammer.controller;

import com.mail.spammer.dto.RequestUserDto;
import com.mail.spammer.dto.ResponceUserDto;
import com.mail.spammer.entities.User;
import com.mail.spammer.repositories.UserRepositoryService;
import com.mail.spammer.services.SecurityUserService;
import com.mail.spammer.services.SecurityUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final SecurityUserService securityUserService;
    private final UserRepositoryService userRepositoryService;

    public UserController(SecurityUserService securityUserService, UserRepositoryService userRepositoryService) {
        this.securityUserService = securityUserService;
        this.userRepositoryService = userRepositoryService;
    }

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity<Void> signInUser() {
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/users/{username}")
    public ResponseEntity<ResponceUserDto> getUserDto(@PathVariable String username) {
        return ResponseEntity.ok(getResponceUserDto(username));
    }

    @CrossOrigin
    @PostMapping("/signup")
    public void registerUser(@RequestBody User user) {
        securityUserService.createUser(getUserDetails(user));
    }

    private SecurityUser getUserDetails(User user) {
        return new SecurityUser(new RequestUserDto(user).build());
    }

    private ResponceUserDto getResponceUserDto(String username) {
        return new ResponceUserDto(userRepositoryService.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("NOT FOUND")));
    }
}
