package com.evozon.mvc.validator;

import com.evozon.domain.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by biancavalean on 7/22/2016.
 */
@Component
public class CategoryValidator implements Validator {

    public boolean supports(Class clazz) {
        return Category.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "name","error.name","Name is required");
        ValidationUtils.rejectIfEmpty(e, "description","error.description","Description is required");

    }

}
