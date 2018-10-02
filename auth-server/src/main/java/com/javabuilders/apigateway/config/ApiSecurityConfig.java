package com.javabuilders.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.servlet.http.HttpServletResponse;


@Configuration
class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(ApiSecurityConfig.class);

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("apiuser")
                .password("{bcrypt}$2a$10$ziQWcZK0JXXvym49MFua5uzgCDTvnFSVZOc.nXew8CebRAl641jnK") //password
                .authorities("READ")
                .and()
                .withUser("admin")
                .password("{bcrypt}$2a$10$7Abhnx6usJZkqkYAy4Clk.GlcCZNBZlW/CLVjHW8Yi.mDmBuJsg/O") //admin
                .authorities("READ", "WRITE");
    }
}
