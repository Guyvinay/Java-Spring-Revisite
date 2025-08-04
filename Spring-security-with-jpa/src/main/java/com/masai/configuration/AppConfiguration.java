package com.masai.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class AppConfiguration {

	@Bean
	public SecurityFilterChain allowURLs(HttpSecurity http) throws Exception {
//		Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> 
//		authorizeHttpRequestsCustomizer = a -> a.requestMatchers(HttpMethod.POST, "/customers").permitAll().anyRequest().authenticated();
		
		http.authorizeHttpRequests(a -> 
			
		a.requestMatchers(HttpMethod.POST, "/customers").permitAll()
		.requestMatchers(HttpMethod.GET , "/customers/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		)
		
		.csrf(c -> c.disable())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults()) ;
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passWordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
