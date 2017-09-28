package com.neosoft;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.neosoft.sportsclubmanagement.config.AppConfig;
import com.neosoft.sportsclubmanagement.dao.UserDao;
import com.neosoft.sportsclubmanagement.model.User;
import com.neosoft.sportsclubmanagement.service.UserService;
import com.neosoft.sportsclubmanagement.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class UserServiceImplTest {
	
	User u;
	@Autowired
	UserService userService;
	
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void testUserService1() throws Exception
	{
		User user = new User();
		user.setUserName("test@123");
		user.setPassword("test123");
		Assert.assertEquals("Welcome",userService.loginUser(user));	
	}

	@Test
	public void testUserService2() throws Exception
	{
		User user = new User();
		user.setUserName("abc@123");
		user.setPassword("abc123");
		Assert.assertEquals("Username Invalid",userService.loginUser(user));	
	}
	
	@Test
	public void testUserService3() throws Exception
	{
		User user = new User();
		user.setUserName("test@123");
		user.setPassword("abc123");
		Assert.assertEquals("Password is incorrect",userService.loginUser(user));	
	}

}
