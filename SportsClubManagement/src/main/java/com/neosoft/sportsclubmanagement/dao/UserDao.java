package com.neosoft.sportsclubmanagement.dao;

import org.springframework.stereotype.Repository;

import com.neosoft.sportsclubmanagement.model.User;

@Repository
public interface UserDao {
	
	public User loginUser(User u) throws Exception;
	public int checkUsername(User u) throws Exception;
}
