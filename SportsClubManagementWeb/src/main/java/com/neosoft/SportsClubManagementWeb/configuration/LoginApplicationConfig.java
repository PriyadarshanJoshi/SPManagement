package com.neosoft.SportsClubManagementWeb.configuration;

import org.primefaces.json.JSONObject;
import org.primefaces.json.JSONString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.neosoft.SportsClubManagementWeb.*"})
@Import(value= { securityConfiguration.class})
public class LoginApplicationConfig {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("index");

    }
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		/*viewResolver.setViewClass(JSONString.class);*/
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".xhtml");
		return viewResolver;
	}
}
