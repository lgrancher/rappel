package com.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.entity.User;
import com.persistence.ConnectionJDBC;
import com.persistence.UserDAO;

public class UserService 
{
	private static UserService userService;
	private static UserDAO userDAO;
	
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
		Connection connection = null;
		User user = null;
		
		try
		{
			connection = ConnectionJDBC.getInstance();
			user = userDAO.retrieve(name);
			// [...] serie d'operations sur la base
			connection.commit();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(connection != null)
			{
				ConnectionJDBC.close(connection);
			}
		}
		
		return user;
	}
}
