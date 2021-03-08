package com.mail.spammer.config;

import com.mail.spammer.repositories.UserRepository;
import com.mail.spammer.repositories.UserRepositoryManager;
import com.mail.spammer.services.JpaUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {
    private final UserRepositoryManager userRepositoryManager;

    public SecurityConfiguration(UserRepositoryManager userRepositoryManager) {
        this.userRepositoryManager = userRepositoryManager;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserService(userRepositoryManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
