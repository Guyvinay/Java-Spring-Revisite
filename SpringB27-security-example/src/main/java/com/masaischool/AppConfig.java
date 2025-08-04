package com.masaischool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	
	
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth->{
			auth
			.requestMatchers("/admin","/user").permitAll()
			.anyRequest()
			.authenticated();
			
		})
		.csrf(csrf->csrf.disable())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	}
	
	
	
//	@Bean
//	public SecurityFilterChain allowURLs(HttpSecurity http) throws Exception {
//		Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> 
//		authorizeHttpRequestsCustomizer = a -> a.requestMatchers("/no_login_needed", "/posts").permitAll().anyRequest().authenticated();
//		
//		//a is an object of AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
//		//https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configurers/AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry.html
//		//https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configurers/AuthorizeHttpRequestsConfigurer.AuthorizedUrl.html
//		
//		http.authorizeHttpRequests(authorizeHttpRequestsCustomizer)
//		.csrf(c -> c.disable())
//		.formLogin(Customizer.withDefaults())
//		.httpBasic(Customizer.withDefaults()) ;
//		return http.build();
//	}
	
//	@Bean
//    public UserDetailsService userDetailsService() {
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("abc")
//                    .password("1234")
//                    .roles("USER")
//                    .build();
//            UserDetails admin = User.withDefaultPasswordEncoder()
//                    .username("xyz")
//                    .password("1234")
//                    .roles("ADMIN", "USER")
//                    .build();
//            return new InMemoryUserDetailsManager(user, admin);
//    }
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
		UserDetails admin = User.withUsername("admin").password("1234").authorities("admin").build();
		UserDetails user = User.withUsername("user").password("1234").authorities("user").build();
		detailsManager.createUser(user);
		detailsManager.createUser(user);
		return detailsManager;
	}
	@Bean
	public PasswordEncoder passEnc() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
