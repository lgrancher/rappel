package com.servlet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.UserService;
import com.validator.UserValidator;

@Controller
@SessionAttributes("user")
@RequestMapping("/register")
public class RegisterServlet {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.addValidators(userValidator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView addUser(@ModelAttribute("userModel") @Valid User userModel, BindingResult result, ModelMap model)
	{
		if(result.hasErrors())
		{
			FieldError error = result.getFieldErrors().get(0);
			String message = error.getDefaultMessage();
			
			model.addAttribute("messageError", message);
			return new ModelAndView("name",model); 
		}
		
		else
		{
			userService.create(userModel);
			
			model.addAttribute("user", userModel);
			return new ModelAndView("welcome",model); 
		}
	}
}
