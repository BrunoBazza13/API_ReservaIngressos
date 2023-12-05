package com.teste.api.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teste.api.model.repository.SecurityFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

	@Autowired
	SecurityFilter securityFilter;

	public SecurityConfig(SecurityFilter securityFilter) {
		super();
		this.securityFilter = securityFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.cors().and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.OPTIONS, "//*").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/usuario/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/usuario/criarUsuario").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/local").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/setor").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/evento/cria").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/usuario/criarUsuario").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/reserva").authenticated()
						.anyRequest().authenticated())

				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();

	}
	
//	 @Bean
//	    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
//	        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
//	        registration.setFilter(new CorsFilter());
//	        registration.addUrlPatterns("/*"); // Define os padrões de URL onde o filtro será aplicado
//	        registration.setName("CorsFilter");
//	        registration.setOrder(1); // Define a ordem de execução do filtro
//	        return registration;
//	    }

	 
	 

//	 @Bean
//	    public CorsWebFilter corsWebFilter() {
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.setAllowCredentials(true);
//	        config.addAllowedOrigin("http://127.0.0.1:5500");
//	        config.addAllowedHeader("*");
//	        config.addAllowedMethod("*");
//	        source.registerCorsConfiguration("/api/**", config);
//
//	        return new CorsWebFilter(source);
//	    }	 
}
