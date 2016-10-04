package com.evozon.mvc.validator;

import com.evozon.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by mihaelarotarescu on 7/19/2016.
 */
@Component
public class ProductValidator  implements Validator {

    public boolean supports(Class clazz) {
        return Product.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "code","error.code","Code is required");
        ValidationUtils.rejectIfEmpty(e, "name","error.name","Name is required");
        ValidationUtils.rejectIfEmpty(e, "price","error.price","Price is required");
        ValidationUtils.rejectIfEmpty(e, "stockLevel","error.stockLevel","Stock is required");

        try {
            Product product = (Product) obj;
            if (product.getPrice() < 0.0) {
                e.rejectValue("price", "error.price", "Wrong input: enter a positive value");
            }
            if (product.getStockLevel() < 0) {
                e.rejectValue("stockLevel", "error.stockLevel", "Wrong input: enter a positive value");
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
    }
}