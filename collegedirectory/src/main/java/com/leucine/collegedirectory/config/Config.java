package com.leucine.collegedirectory.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebSecurity
public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                    configuration.setAllowedMethods(Collections.singletonList("*"));
                    configuration.setAllowCredentials(true);
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                    return configuration;
                }))
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/swagger-ui*/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/admin/addfaculty", "/api/admin/addstudent", "/api/admin/addadmin").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/admin/student/{id}", "/api/admin/faculty/{id}").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/admin/student/{id}", "/api/admin/faculty/{id}").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/faculty/**").hasRole("FACULTY_MEMBER")
//                    .hasRole("FACULTY_MEMBER")
                    .requestMatchers(HttpMethod.GET, "/api/department/**").permitAll()
                    .requestMatchers(HttpMethod.PUT, "/api/faculty/**").hasRole("FACULTY_MEMBER")
                    .requestMatchers(HttpMethod.GET, "/api/student/**").hasRole("STUDENT")
                    .requestMatchers(HttpMethod.GET, "/auth/logini").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/auth/logIn").permitAll()
                    .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .addFilterBefore(new JwtTokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new JwtTokenGeneratorFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
