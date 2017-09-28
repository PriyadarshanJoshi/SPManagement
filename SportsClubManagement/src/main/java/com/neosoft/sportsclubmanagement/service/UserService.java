package com.neosoft.sportsclubmanagement.service;

import org.springframework.stereotype.Service;

import com.neosoft.sportsclubmanagement.model.User;

@Service
public interface UserService {

	public String loginUser(User u) throws Exception;
}
