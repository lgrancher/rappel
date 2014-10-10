package com.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.persistence.UserDAO;

public class NameServlet extends HttpServlet
{
	public void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("WEB-INF/name.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nom = req.getParameter("nom"); 
		System.out.println("nom = " + nom);
		
		UserDAO userDAO = UserDAO.getInstance();
		User user = null;
		
		try 
		{
			user = userDAO.retrieve(nom);
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(user != null)
		{
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("welcome");
		}
		
		else
		{
			req.setAttribute("message", "mauvais nom !");
			resp.sendRedirect("index");
		}
	}
}
