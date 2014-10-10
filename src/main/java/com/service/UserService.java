package com.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.persistence.ConnectionJDBC;
import com.persistence.UserDAO;

@Service
public class UserService 
{
	@Autowired
	private UserDAO userDAO;
	
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

	public UserDAO getUserDAO() 
	{
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) 
	{
		this.userDAO = userDAO;
	}
}
