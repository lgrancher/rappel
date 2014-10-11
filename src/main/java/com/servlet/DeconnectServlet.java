package com.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/deconnect")
public class DeconnectServlet
{
	@RequestMapping(method = RequestMethod.GET)
	protected String deconnect(WebRequest request, SessionStatus status){
	
		status.setComplete();
	    request.removeAttribute("user", WebRequest.SCOPE_SESSION);	
		
		return "name";
	}
}
