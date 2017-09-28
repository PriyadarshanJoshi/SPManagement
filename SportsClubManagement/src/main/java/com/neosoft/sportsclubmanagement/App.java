package com.neosoft.sportsclubmanagement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.neosoft.sportsclubmanagement.config.AppConfig;
import com.neosoft.sportsclubmanagement.dao.UserDao;
public class App {

	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserDao userDao = (UserDao) context.getBean("userDao");
}
}
