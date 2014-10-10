package com.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;
import com.service.UserService;

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
		
		UserService userService = UserService.getInstance();
		User user = userService.retrieve(nom);
		
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
