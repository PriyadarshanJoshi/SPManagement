package com.neosoft.SportsClubManagementWeb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Sonu").password("abc123").roles("USER");
		/*auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
	  /*http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.anyRequest().authenticated()
	  	.and().formLogin().loginPage("/index")
	  	.permitAll()
        .and()
        .logout()
        .permitAll()
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/error.xhtml");*/
		
		http.authorizeRequests()
		  .antMatchers("/","/home").permitAll()
		  .and()
		  .formLogin().loginPage("/index").failureUrl("/index?error")
		  .usernameParameter("username").passwordParameter("password")
		  .and()
		  .logout().logoutSuccessUrl("/index?logout")
		  .and()
		  .exceptionHandling().accessDeniedPage("/403")
		  .and()
		  .csrf();
	}
}
