package com.evozon.mvc.validator;

import com.evozon.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by biancavalean on 7/22/2016.
 */
@Component
public class UserValidator implements Validator {

    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "email", "error.email", "Email is required");
        ValidationUtils.rejectIfEmpty(e, "username", "error.username", "Username is required");
        ValidationUtils.rejectIfEmpty(e, "password", "error.password", "Password is required");

        User user = (User) obj;

        if(user.getPassword().length()<6){
            e.rejectValue("password","error.password","Password must have at least 6 characters");
        }
    }
}
