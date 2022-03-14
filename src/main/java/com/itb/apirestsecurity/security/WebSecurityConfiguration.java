package com.itb.apirestsecurity.security;

import com.itb.apirestsecurity.model.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyEntryPointConfiguration myEntryPointConfiguration;
    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder xifrat;

//Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }

//codi per fer una prova autenticant en memòria "inMemoryAuthentication()"
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(xifrat)
//                .withUser("Montse")
//                .password(xifrat.encode("secret"))
//                .roles("ADMIN"); // és necessari posar tots els camps, fins el rol (authorities)
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(xifrat);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .httpBasic()
                .authenticationEntryPoint(myEntryPointConfiguration)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN_USER") //per fer proves del forbidden
                .antMatchers(HttpMethod.GET, "/users/**", "/students/**").hasRole("BASE_USER")
                .antMatchers(HttpMethod.POST, "/users/**", "/students/**").hasRole("BASE_USER")
                .antMatchers(HttpMethod.PUT, "/students/**").hasRole("BASE_USER")
                .antMatchers(HttpMethod.DELETE, "/students/**").hasRole("ADMIN_USER")
                .antMatchers(HttpMethod.POST, "/students/**").hasAnyRole("BASE_USER", "ADMIN_USER")
                .anyRequest().authenticated();
        // .and()
        // .csrf().disable();
    }

}
