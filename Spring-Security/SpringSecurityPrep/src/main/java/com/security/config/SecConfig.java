package com.security.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.security.jwt.JwtTokenGeneratorFilter;
import com.security.jwt.JwtTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
    	CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();

    	
		http
		.sessionManagement(sessionManagement->
		       sessionManagement
		       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
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
			.requestMatchers(HttpMethod.POST, "/customers").permitAll()
			.requestMatchers(HttpMethod.POST, "/signIn").permitAll()
			.requestMatchers("/hello").permitAll()
			.requestMatchers(HttpMethod.GET,"/customers").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.GET,"/customers","/hello").hasAnyAuthority("VIEWALLCUSTOMER")
//			.requestMatchers(HttpMethod.GET,"/customers/**").hasAnyAuthority("VIEWALLCUSTOMER","VIEWCUSTOMER")
			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			.anyRequest().authenticated();
		})
		.csrf(csrf->csrf.disable())
//		.csrf(csrf->csrf.ignoringRequestMatchers("/contact","/notice"))
//		.csrf(csrf->
//		           csrf
//		           .csrfTokenRequestHandler(requestHandler)
//		           .ignoringRequestMatchers("/contact","/notice","/customers")
//		           .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//		           )
//		.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//		.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
//		.addFilterAfter(new AuthoritiesLogginAfterFilter(), BasicAuthenticationFilter.class)
//		.addFilterAfter(new CustomOncePerRequestFilter(), BasicAuthenticationFilter.class)
//		.addFilterAt(new LoggingFilterAt(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration
				.getAuthenticationManager();
	}
	
}
