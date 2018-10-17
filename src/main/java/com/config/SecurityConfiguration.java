package com.config;

import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.nio.file.AccessDeniedException;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(myPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().loginPage("/login").loginProcessingUrl("/login").
                successHandler(myAuthSuccessHandler()).
                usernameParameter("userLogin").passwordParameter("userPassword").permitAll().
                and().
                authorizeRequests().
                antMatchers("/allUsers", "/editUser").access("hasRole('ADMIN')").
                and().
                rememberMe().tokenValiditySeconds(600).key("AppNameSecurity").
                and().
                exceptionHandling().accessDeniedHandler(myAccessDeniedHandler());
    }

    @Bean
    public AuthenticationSuccessHandler myAuthSuccessHandler(){
        return new CustomSimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder myPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler myAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

}
