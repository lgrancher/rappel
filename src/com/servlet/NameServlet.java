package com.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NameServlet extends HttpServlet
{
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String nom = request.getParameter("nom"); 
		System.out.println("nom = " + nom);
		request.setAttribute("nom", nom);
		request.getRequestDispatcher("name.jsp").forward(request, response);
	}
}
