package com.mail.spammer.config;

import com.mail.spammer.security.filters.CustomAuthenticationFilter;
import com.mail.spammer.security.providers.UserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationFilter filter;
    private final UserAuthenticationProvider provider;

    public WebSecurityConfiguration(CustomAuthenticationFilter filter, UserAuthenticationProvider provider) {
        this.filter = filter;
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(filter, BasicAuthenticationFilter.class);
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().cors()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
