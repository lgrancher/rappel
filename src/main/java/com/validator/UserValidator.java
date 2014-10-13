package com.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.entity.User;

@Component
public class UserValidator implements Validator
{
	public boolean verifyNom(String name)
	{
		boolean goodLength = name.length() < 4 ? false : true;
		
		return goodLength;
	}
	
	@Override
	public boolean supports(Class<?> arg0) {

		return User.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{	
		User user = (User) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty","You have not answered all required fields");		
	
		if(!verifyNom(user.getName()))
		{
			errors.rejectValue("name", "invalid", "You have not given a correct name");
		}
	}
}
