package com.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.entity.User;

@Controller
@SessionAttributes("user")
@RequestMapping("/other")
public class OtherServlet
{
	@RequestMapping(method = RequestMethod.GET)
	protected String other(ModelMap model){
		
		User user = (User) model.get("user");
		
		if(user != null)
		{
			user.setName(user.getName().toUpperCase());
		}
		
		return "other";
	}
}
