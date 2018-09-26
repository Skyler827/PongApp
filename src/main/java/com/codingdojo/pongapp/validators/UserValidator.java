package com.codingdojo.pongapp.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.pongapp.models.User;
import com.codingdojo.pongapp.services.UserService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        } 
        if(userService.emailExists(user.getEmail())) {
        	errors.rejectValue("username", "Duplicate");
        }
    }
}