package com.example.springsecuritymodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/h2-console/**", "/getHealthcheck").permitAll().and().csrf()
				.ignoringAntMatchers("/h2-console/**", "/getHealthcheck").and().headers().frameOptions().sameOrigin();
		httpSecurity.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint());
		httpSecurity.headers().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000);
		httpSecurity.headers().contentSecurityPolicy("script-src 'self'");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
		authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("user").roles("USER");
		authenticationManagerBuilder.inMemoryAuthentication().withUser("user1").password("user1").roles("USER");
		authenticationManagerBuilder.inMemoryAuthentication().withUser("user3").password("user3").roles("USER");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
