package com.leodemetrio.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticateService authenticateService;

    public SecurityConfiguration(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(authenticateService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topics").permitAll()
                .antMatchers(HttpMethod.GET, "/topics/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       // super.configure(web);
    }
}
