package com.exadelinternship.carpool.config;

import com.exadelinternship.carpool.services.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Bean
    PasswordEncoder passwordEncoder()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/profile/cars").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
        http.csrf().disable();
        http.formLogin().usernameParameter("j_username")
                .passwordParameter("j_password");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider(authProvider);
    }
}