package com.evozon.domain.dtos;

import com.evozon.domain.Address;
import com.evozon.domain.Cart;
import com.evozon.domain.Payment;
import org.springframework.security.access.method.P;

/**
 * Created by iuliacodau on 26/07/2016.
 */
public class OrdersDTO {

    private int cartId;

    private String deliveryStreet;

    private int deliveryNumber;

    private String deliveryCity;

    private String deliveryPhone;

    private String billingStreet;

    private int billingNumber;

    private String billingCity;

    private String billingPhone;

    private String paymentMethod;

    private String cardNumber;

    private String cardCode;

    private String email;

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public int getBillingNumber() {
        return billingNumber;
    }

    public void setBillingNumber(int billingNumber) {
        this.billingNumber = billingNumber;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void getDataFromCart(Cart cart) {
        if (cart.getCartId() != 0) {
            this.cartId = cart.getCartId();
        }
        if (cart.getDeliveryAddress() != null) {
            this.deliveryStreet = cart.getDeliveryAddress().getStreet();
            this.deliveryNumber = cart.getDeliveryAddress().getNumber();
            this.deliveryCity = cart.getDeliveryAddress().getCity();
            this.deliveryPhone = cart.getDeliveryAddress().getPhone();
        }
        if (cart.getBillingAddress() != null) {
            this.billingStreet = cart.getBillingAddress().getStreet();
            this.billingNumber = cart.getBillingAddress().getNumber();
            this.billingCity = cart.getBillingAddress().getCity();
            this.billingPhone = cart.getBillingAddress().getPhone();
        }
        if (cart.getPayment() != null) {
            this.paymentMethod = cart.getPayment().getPaymentMethod();
            this.cardNumber = cart.getPayment().getCardNumber();
            this.cardCode = cart.getPayment().getCardCode();
        }
        if (cart.getEmail() != null || cart.getEmail() != "") {
            this.email = cart.getEmail();
        }
    }
}
