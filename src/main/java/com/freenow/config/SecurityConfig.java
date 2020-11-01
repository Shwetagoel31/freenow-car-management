/*package com.freenow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
*/
//@Configuration
//@EnableWebSecurity
/*public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //auth.userDetailsService(userDetailsService);
        auth
            .inMemoryAuthentication()
            .withUser("foo")
            .password("bar")
            .roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //@formatter:off

        http
            .authorizeRequests()
            .antMatchers("/", "/login", "/h2-console/***")
            .permitAll()
            .antMatchers("/api/v1/drivers")
            .hasAnyRole("ADMIN", "DRIVER")
            .antMatchers("/api/v1/cars")
            .hasRole("ADMIN")
            .and()
            .formLogin();
          .defaultSuccessUrl("/swagger-ui.html");
        
        //@formatter:on

    }


    @Bean
    public PasswordEncoder BCryptPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

}*/
