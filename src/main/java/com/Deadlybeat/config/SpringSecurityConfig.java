package com.Deadlybeat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		auth.inMemoryAuthentication().withUser("Deadlybeat").password("akash").roles("Admin");
		auth.inMemoryAuthentication().withUser("Aayushiii").password("aayushi").roles("user");
	}
	
	//method for cross origin
	//this method is for Controller specific Authentication for ALL APIs
	/*
	 * @Override 
	 * protected void configure(HttpSecurity http) throws Exception {
	 * //disable CSRF cross site request Forgery http.csrf() .disable(); 
	 * //for every Request we need Authentication so anyRequest()
	 * http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); }
	 */
	
	//URL specific Security Authentication
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * //disable CSRF cross site request Forgery 
	 * http.csrf() .disable(); //for every
	 * Request we need Authentication so anyRequest() 
	 * //security only for URL starting from /Auth/..
	 * http.authorizeRequests().antMatchers("/Auth/**").authenticated().and().httpBasic(); 
	 * }
	 */
	 
	 //Role based Security for particular API's
	
	//when we try to hit any link with /Auth by any user role 403  error should thorw as unauthorized access
	 protected void configure(HttpSecurity http) throws Exception {
		 //disable CSRF cross site request Forgery 
		 http.csrf() .disable(); 
		 //for every Request we need Authentication so anyRequest()
		 //security only for URL starting from /Auth/.. 
		 http.authorizeRequests().antMatchers("/Auth/**")
		 	.hasAnyRole("Admin")
		 	.anyRequest()
		 	.fullyAuthenticated()
		 	.and().
		 	httpBasic(); 
		 }

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
	
	//as we using springBoot 2.0 the password will be needed in encrypted form so this method
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
