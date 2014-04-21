package com.taskkeeper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// @formatter:off
		auth
    	.userDetailsService(userDetailsService)
    		.passwordEncoder(passwordEncoder());
	// @formatter:on
    		
		/*
		auth
			.inMemoryAuthentication()
				.withUser("letsnosh")
				.password("noshing")
				.roles("USER");
		*/
	}
	
	
  @Override
  public void configure(WebSecurity web) throws Exception {
 // @formatter:off
    web
      .ignoring()
      	.antMatchers("/resources/**");
 // @formatter:on
  }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
    http
    	.formLogin()
    		.loginPage("/login")
    		.permitAll()
    		.and()
    	.logout()
      	.permitAll()
      	.and()
    	.authorizeRequests()
        //.antMatchers("/home").hasRole("USER")
        //.antMatchers("/aggregators/**").hasRole("USER")
        .anyRequest().anonymous()
        //.anyRequest().authenticated()
        .and()
      .csrf()
    		.disable(); // Fix this !!!
    // @formatter:on
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
