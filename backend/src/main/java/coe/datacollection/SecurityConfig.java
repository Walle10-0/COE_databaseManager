package coe.datacollection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/api/login", "/api/register").permitAll() // Allow unauthenticated access
                .requestMatchers("/private/**").authenticated()
                .anyRequest().authenticated()
            )
            // Removed .formLogin(Customizer.withDefaults()) to disable default form login
            .logout(logout -> logout
                .logoutUrl("/logout")  // Custom logout URL, can be adjusted as needed
                .logoutSuccessUrl("/login?logout")  // Redirect after successful logout
                .permitAll()  // Allow all users to access the logout URL
            )
            //.httpBasic(Customizer.withDefaults()) // Removed to disable HTTP Basic authentication
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors
                .configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:3000"));
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                })
            );
        return http.build();
    }
}
