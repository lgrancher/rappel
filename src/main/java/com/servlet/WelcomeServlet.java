package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class WelcomeServlet
{
	@RequestMapping(method = RequestMethod.GET)
	protected String welcome()
			throws ServletException, IOException 
	{		
		return "welcome";
	}
}
