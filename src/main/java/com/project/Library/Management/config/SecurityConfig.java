package com.project.Library.Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/api/v1/books/**").hasAnyRole("LIBRARIAN","PATRON")
                        .requestMatchers(HttpMethod.POST,"/api/v1/books/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/books/**").hasAnyRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/books/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.GET,"/api/v1/patrons/**").hasAnyRole("LIBRARIAN","PATRON")
                        .requestMatchers(HttpMethod.POST,"/api/v1/patrons/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/patrons/**").hasAnyRole("PATRON","LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/patrons/**").hasAnyRole("PATRON","LIBRARIAN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/borrow/**").hasRole("PATRON")
                        .requestMatchers(HttpMethod.POST,"/api/v1/return/**").hasRole("PATRON")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf-> csrf.disable());
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
