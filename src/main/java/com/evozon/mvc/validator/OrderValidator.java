package com.evozon.mvc.validator;

import com.evozon.domain.Orders;
import com.evozon.domain.dtos.OrdersDTO;
import org.hibernate.validator.constraints.impl.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iuliacodau on 28/07/2016.
 */

@Component
public class OrderValidator implements Validator{
    @Override
    public boolean supports(Class clazz) {
        return OrdersDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "email", "error.email", "E-mail is required");
        ValidationUtils.rejectIfEmpty(e, "deliveryStreet", "error.deliveryStreet", "Delivery street is required");
        ValidationUtils.rejectIfEmpty(e, "deliveryNumber", "error.deliveryNumber", "Delivery number is required");
        ValidationUtils.rejectIfEmpty(e, "deliveryCity", "error.deliveryCity", "Delivery city is required");
        ValidationUtils.rejectIfEmpty(e, "deliveryPhone", "error.deliveryPhone", "Delivery phone is required");
        ValidationUtils.rejectIfEmpty(e, "billingStreet", "error.billingStreet", "Billing street is required");
        ValidationUtils.rejectIfEmpty(e, "billingNumber", "error.billingNumber", "Billing number is required");
        ValidationUtils.rejectIfEmpty(e, "billingCity", "error.billingCity", "Billing city is required");
        ValidationUtils.rejectIfEmpty(e, "paymentMethod", "error.paymentMethod", "Payment method is required");

        try {
            OrdersDTO orders = (OrdersDTO) o;
            Pattern pattern = Pattern.compile("^.+@.+\\..+$");
            Matcher matcher = pattern.matcher(orders.getEmail());
            if (!matcher.matches()) {
                e.rejectValue("email", "error.email", "Incorrect e-mail format");
            }
            if (!orders.getDeliveryPhone().matches("[0-9]+")) {
                e.rejectValue("deliveryPhone", "error.deliveryPhone", "Introduce only numbers");
            }
            if (!orders.getBillingPhone().matches("[0-9]+")) {
                e.rejectValue("billingPhone", "error.billingPhone", "Introduce only numbers");
            }
            if (orders.getPaymentMethod().matches("CARD")) {
                if (!orders.getCardNumber().matches("[0-9]+")) {
                    e.rejectValue("cardNumber", "error.cardNumber", "Introduce only numbers");
                }
                if (!orders.getCardCode().matches("[0-9]+")) {
                    e.rejectValue("cardCode", "error.cardCode", "Introduce only numbers");
                }
            }
        } catch (Exception error) {
            error. printStackTrace();
        }
    }
}
