package com.neosoft.sportsclubmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.neosoft.sportsclubmanagement.dao.UserDao;
import com.neosoft.sportsclubmanagement.model.User;
import com.neosoft.sportsclubmanagement.service.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	public UserServiceImpl(UserDao userDao)
	{
		this.userDao=userDao;
	}
	
	
	public UserServiceImpl() {

	}


	public String loginUser(User u) throws Exception {
		int checkUserName;
		User user=new User();
		checkUserName=userDao.checkUsername(u);
		if(checkUserName==0)
		{
			return "Username Invalid";
		}
		
		else
		{
			user=userDao.loginUser(u);
			{
				if(user==null)
					return "Password is incorrect";
			}
		return "Welcome";	
		}
	}
	
	

}
