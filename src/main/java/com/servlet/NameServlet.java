package com.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.UserService;

@Controller
@SessionAttributes("user")
public class NameServlet
{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String index()
	{
		return "redirect: index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String redirectName()
	{
		return "name";	
	}
	
	@RequestMapping(value="/name", method = RequestMethod.POST)
	protected ModelAndView connectUser(@ModelAttribute("userModel") User userModel, ModelMap model){
		
		User user = userService.retrieve(userModel.getNom());
		
		if(user != null)
		{
			model.addAttribute("user", user);
			return new ModelAndView("welcome",model); 
		}
		
		else
		{
			model.addAttribute("message", "mauvais nom !");
			return new ModelAndView("name", model); 
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
}
