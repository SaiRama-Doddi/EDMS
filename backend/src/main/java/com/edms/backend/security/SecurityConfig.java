package com.edms.backend.security;



import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for development
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() 
                .requestMatchers("/api/uploads/**").permitAll() // Allow all upload APIs
                .requestMatchers("/api/forms/**").permitAll()
                .requestMatchers("/api/forms/document/**", "/api/forms/region/**", "/api/forms/drawing/**").permitAll()

                
                .requestMatchers("/api/auth/create-draftsman").hasAnyAuthority("HEXTA")// Allow auth API without security
                .requestMatchers("/api/auth/draftsmen").hasAnyRole("HEXTA", "GAIL", "ADMIN") 
                .requestMatchers("/api/details/**").permitAll() 
                .anyRequest().authenticated()
            );

        return http.build();
    }



     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
