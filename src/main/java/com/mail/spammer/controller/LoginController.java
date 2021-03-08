package com.mail.spammer.controller;

import com.mail.spammer.entities.User;
import com.mail.spammer.services.RegisterNewUser;
import com.mail.spammer.services.SecurityUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final RegisterNewUser registerNewUser;

    public LoginController(RegisterNewUser registerNewUser) {
        this.registerNewUser = registerNewUser;
    }

    @GetMapping("/login")
    public String signInUser() {
        return "Hello";
    }

    @PostMapping("/signup")
    public void registerUser(@RequestBody User user) {
        registerNewUser.createUser(new SecurityUser(user));
    }
}
