package com.mail.spammer.dto;

import com.mail.spammer.entities.User;

public class RequestUserDto {
    private final String username;
    private final String password;

    public RequestUserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User build() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }
}
