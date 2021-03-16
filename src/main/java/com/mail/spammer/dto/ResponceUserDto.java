package com.mail.spammer.dto;


import com.mail.spammer.entities.User;

public class ResponceUserDto {
    private String username;
    private String password;

    public ResponceUserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
