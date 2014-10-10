package com.servlet;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.entity.User;
import com.service.UserService;

public class NameServlet extends HttpServlet
{
	@Autowired
	private UserService userService;
	
	public void doGet( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.getRequestDispatcher("WEB-INF/name.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String nom = req.getParameter("nom"); 
		System.out.println("nom = " + nom);
		
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

	public UserService getUserService() 
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
	}
}
