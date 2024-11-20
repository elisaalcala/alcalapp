package com.app.alcala.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.alcala.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
    public UserDetailsServiceImpl userDetailService;

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.authorizeHttpRequests(authorize -> authorize
					// PUBLIC PAGES
					.requestMatchers("/login", "/authenticate", "/WEB-INF/jsp/login.jsp", "/favicon.ico").permitAll()
					.requestMatchers("/**","/WEB-INF/jsp/**").authenticated()
			)
			.logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login")
			);
		
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

}