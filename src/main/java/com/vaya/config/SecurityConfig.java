package com.vaya.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaya.repositories.MemberRepository;
import com.vaya.services.MemberUserService;


@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true )
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/").hasRole("ADMIN")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}
	
	@Autowired
	private MemberRepository memberRepository;
	
	// * This configureGlobal method is used to authenticate users from the AuthorUserService.
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(new MemberUserService(memberRepository))
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//This method is used for inMemoryAuthentication()
/*	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("Joe").password("1234").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("Minhee").password("1234").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("kans").password("1234").roles("USER");
	}
	*/
}