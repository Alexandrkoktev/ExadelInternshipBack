package com.exadelinternship.carpool.config;

import com.exadelinternship.carpool.entity.enums.UserRole;
import com.exadelinternship.carpool.services.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionManagementFilter;


import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthProvider authProvider;

    @Bean
    PasswordEncoder passwordEncoder()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
    @Bean
    Filter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .addFilterBefore(corsFilter(), SessionManagementFilter.class);
        http
                .authorizeRequests()
                .antMatchers("/api/statistic").hasRole("ADMINISTRATOR")
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/api/login")
                .permitAll()
                .and().logout().logoutUrl("/api/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
                        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
                        response.setHeader("Access-Control-Allow-Headers", "*");
                        response.setHeader("Access-Control-Allow-Credentials", "true");
                        response.setHeader("Access-Control-Max-Age", "180");
                    }
                })
                .permitAll();
        http.csrf().disable();
        http.formLogin().usernameParameter("j_username")
                .passwordParameter("j_password");
        http.formLogin().successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendError(200);
            }
        });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }

}