package com.itb.apirestsecurity.security;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;


@Component
@RequiredArgsConstructor
public class MyEntryPointConfiguration extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //super.commence(request, response, authException);
        response.addHeader("WWW-Authenticate", "Basic realm=\\" + getRealmName() + "\\");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter writer = response.getWriter();
        writer.println("Accés no autoritzat");

    }

    @PostConstruct
    public void initRealmname() {
        setRealmName("users.itb");
    }
}