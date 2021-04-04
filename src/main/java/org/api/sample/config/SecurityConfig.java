package org.api.sample.config;

import lombok.RequiredArgsConstructor;
import org.api.sample.security.JwtAuthenticationFilter;
import org.api.sample.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .authorizeRequests()
                        .antMatchers("/exError", "/api/test").permitAll()
                        .antMatchers("/h2-console/*").permitAll()
                        .antMatchers("/").permitAll()
                        .antMatchers("/api/login").permitAll()
                        .antMatchers("/v3/**","/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**").permitAll()
                        .anyRequest().hasRole("USER")
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
