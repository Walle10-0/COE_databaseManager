package coe.datacollection;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig { 
  
    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
			.securityMatcher("/api/**")
			.authorizeHttpRequests((requests) -> requests
			    .requestMatchers("/api/users").hasRole("dean")
				.requestMatchers("/api/departments").hasRole("dean")
                //.requestMatchers("/api/user/{id}").access(userService.canAccessUser(authentication, {id}))
                //.requestMatchers("/api/department/{id}/users").access("@userService.canAccessDepartment(authentication, #id)")
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
			.logout((logout) -> logout.permitAll())
            .build();
    }
}