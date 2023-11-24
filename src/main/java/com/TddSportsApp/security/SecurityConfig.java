package com.TddSportsApp.security;

import com.TddSportsApp.security.filters.JwtAuthenticationFilter;
import com.TddSportsApp.security.filters.JwtAuthorizationFilter;
import com.TddSportsApp.security.jwt.JwtUtils;
import com.TddSportsApp.service.UserDetailsServiceImpl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity()
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtAuthorizationFilter authorizationFilter;

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/api-docs/**",
            "/swagger-ui/**",
            "/swagger",
            // other public endpoints of the API
            "users/register",
            "/login",
            "/"
    };

    private static final String ADMIN = "ADMIN";

    public HttpSecurity addPermissions(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and()
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
            auth.requestMatchers(AUTH_WHITELIST).permitAll();
            auth.requestMatchers(HttpMethod.POST, "/events")
                    .hasAuthority(ADMIN);
            auth.requestMatchers(HttpMethod.DELETE, "/events/{id}", "/users/{id}", "/results/{id}")
                    .hasAuthority(ADMIN);
            auth.requestMatchers(HttpMethod.PUT, "/events/{id}")
                    .hasAuthority(ADMIN);
            auth.requestMatchers(HttpMethod.GET, "/users/me")
                    .authenticated();
            auth.requestMatchers(HttpMethod.GET, "/users", "/users/{id}")
                    .hasAuthority(ADMIN);


            auth.anyRequest().authenticated();
        });
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager)
            throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return addPermissions(httpSecurity)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
