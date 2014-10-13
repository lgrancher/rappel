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
			connection = ConnectionJDBC.INSTANCE.getConnection();
			user = userDAO.findByName(name);
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			if(connection != null)
			{
				ConnectionJDBC.INSTANCE.close(connection);
			}
		}
		
		return user;
	}
	
	public void create(User user)
	{
		Connection connection = null;
		System.out.println(user);

		try
		{
			connection = ConnectionJDBC.INSTANCE.getConnection();
			userDAO.save(user);
			// [...] serie d'operations sur la base
			connection.commit();
		} 
		
		catch (SQLException e) 
		{
			try 
			{
				connection.rollback();
			} 
			
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		finally
		{
			if(connection != null)
			{
				ConnectionJDBC.INSTANCE.close(connection);
			}
		}		
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
