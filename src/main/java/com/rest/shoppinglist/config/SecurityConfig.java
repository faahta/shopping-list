package com.rest.shoppinglist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Fassil on 05/12/20.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers( "/v1/confirm-account/**").permitAll()
                .antMatchers("/v1/authenticate/**").permitAll()
                .antMatchers("/v1/user/me/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/oauth2/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/login/oauth2/code/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/v1/forgotPassword/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/v1/confirm-reset/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/v1/reset-password/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/v1/register/**").permitAll()
                .antMatchers( HttpMethod.OPTIONS, "/v1/api/**").fullyAuthenticated();
//                .antMatchers( HttpMethod.GET,"/v1/api/**").fullyAuthenticated()
//                .antMatchers( HttpMethod.PUT,"/v1/api/**").fullyAuthenticated()
//                .antMatchers( "/v1/api/**").fullyAapt uthenticated()

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
