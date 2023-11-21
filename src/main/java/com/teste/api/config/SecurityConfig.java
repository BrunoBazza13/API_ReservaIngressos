package com.teste.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//        		.csrf(csrf -> csrf.disable())
//        		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        		.authorizeHttpRequests(authorize -> authorize
//        				.requestMatchers(HttpMethod.POST,  "/api/usuario/login").permitAll()
//        				.requestMatchers(HttpMethod.POST,  "/api/reserva").permitAll()
//        				.anyRequest().authenticated())
//        		.build();
//        
//    }



