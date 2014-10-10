package com.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.User;

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
		
		if(nom != null && (nom.equals("laura") || nom.equals("Laura")))
		{
			req.getSession().setAttribute("user", new User(nom));
			resp.sendRedirect("welcome");
		}
		
		else
		{
			req.setAttribute("message", "mauvais nom !");
			resp.sendRedirect("index");
		}
	}
}
