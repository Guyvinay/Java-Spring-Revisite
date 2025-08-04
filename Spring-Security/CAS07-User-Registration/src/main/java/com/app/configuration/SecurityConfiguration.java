package com.app.configuration;

import java.security.Key;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.app.service.JwtAuthenticationFilter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		  .sessionManagement( 
				session -> 
		        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .cors(cors->{
				cors.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration cfg = new CorsConfiguration();
						cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(Arrays.asList("Authorization"));
						return cfg;
					}
				});
			})
		  .authorizeHttpRequests(auth->{
			   auth
			     .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
			     .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
			     .requestMatchers(HttpMethod.GET, "/users/getAllUsers","users/details").permitAll()
			     .requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			         .anyRequest()
			         .authenticated();
		   })
		  .csrf(csrf->csrf.disable())
//		  .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		  .formLogin(Customizer.withDefaults())
		  .httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
		
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	Key jwtKey() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	
}
