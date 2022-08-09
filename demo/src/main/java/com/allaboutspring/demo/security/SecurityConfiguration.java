package com.allaboutspring.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private CustomUserDetailsService myUserDetailsService;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
 
//    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeRequests()
       			.antMatchers("/login")
       			.permitAll()
       		.anyRequest()
       			.authenticated()
		    .and()
		    	.formLogin()
		    	.loginPage("/login")
		        .usernameParameter("email")
		        .permitAll()
		    .and()
		    	.logout()
		    	.permitAll();
		
		http.headers().frameOptions().sameOrigin();
		return http.build();
    }
 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig
    ) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(myUserDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
}

/*
This is the main security configuration class which is why annotated with @EnableWebSecurity

For configuring security, we can extend WebSecurityConfigurerAdapter where we need to override 2 methods:
1. configure(AuthenticationManagerBuilder auth) - this does authentication - verifying the identify of a user
2. configure(HttpSecurity http) - this does authorization - verifying the access control of a user

NOTE: Since Spring Boot 2.7.0 and Spring Security 5.7.1 WebSecurityConfigurerAdapter is deprecated
In order to configure without WebSecurityConfigurerAdapter we need to add 2 beans:
1. SecurityFilterChain filterChain(HttpSecurity http) - same as configure(HttpSecurity http), remember to call build() at the end
2. WebSecurityCustomizer webSecurityCustomizer() - here we are using it to ignore static urls such as for js and images

If we need AuthenticationManager, we can add following bean:
@Bean
public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
}

In order to set our custom authentication provider with user details service we have added one more bean DaoAuthenticationProvider
Here we have configured our custom user details service and also password encoder

Check custom user details service comments for entire security flow

*/