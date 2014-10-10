package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;

public class WelcomeServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		User user = (User) req.getSession().getAttribute("user");
		
		if(user != null)
		{
			req.setAttribute("name", user.getNom());
		}
		
		req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
	}
}
