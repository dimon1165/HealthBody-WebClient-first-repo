package edu.softserve.healthbody.webclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
	        .withUser("user").password("password").roles("USER")
	        .and()
	        .withUser("admin").password("password").roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/Login*", "/", "/HomePage.html", "/resources/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/Login.html")
					.defaultSuccessUrl("/HomePage.html")
					.failureUrl("/Login.html?error=true")
			.and()
				.logout()
					.logoutSuccessUrl("/Login.html")
				;
    }
}