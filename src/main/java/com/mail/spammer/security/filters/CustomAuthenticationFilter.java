package com.mail.spammer.security.filters;

import com.mail.spammer.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter implements Filter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String name = httpRequest.getHeader("username");
        String password = httpRequest.getHeader("password");

        CustomAuthentication auth = new CustomAuthentication(name, password);

        try {
            final Authentication authenticate = authenticationManager.authenticate(auth);
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                chain.doFilter(request, response);
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (AuthenticationException exception) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
