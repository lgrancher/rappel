package com.service;

import com.entity.User;
import com.persistence.ConnectionJDBC;
import com.persistence.UserDAO;

public class UserService 
{
	private static UserService userService;
	private static UserDAO userDAO;
	private static ConnectionJDBC connectionJDBC;
	
	private UserService()
	{
		userDAO = UserDAO.getInstance();
	}
	
	public static UserService getInstance()
	{
		if(userService == null)
		{
			userService = new UserService();
		}
		
		return userService;	
	}
	
	public User retrieve(String name)
	{
		User user = userDAO.retrieve(name);
		
		return user;
	}
}
