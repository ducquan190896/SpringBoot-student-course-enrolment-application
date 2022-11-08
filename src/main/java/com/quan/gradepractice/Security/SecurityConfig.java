package com.quan.gradepractice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.quan.gradepractice.Entity.RoleType;
import com.quan.gradepractice.Security.Filter.ExceptionFilter;
import com.quan.gradepractice.Security.Filter.JwtAuthorization;
import com.quan.gradepractice.Security.Filter.UserAuthentication;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomerAuthenticationManager customerAuthenticationManager;

    @Autowired
    ExceptionFilter exceptionFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UserAuthentication userAuthentication = new UserAuthentication(customerAuthenticationManager);
        userAuthentication.setFilterProcessesUrl("/login");
        http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(exceptionFilter, UserAuthentication.class)
        .addFilter(userAuthentication)
        .addFilterAfter(new JwtAuthorization(), UserAuthentication.class)
        .authorizeRequests()
         .antMatchers(HttpMethod.POST
         , "/ap1/v1/users/registration").permitAll()
        .anyRequest().authenticated()
        ;
        return http.build();
    }
}
