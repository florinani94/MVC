package com.evozon.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders extends Cart{

    public Orders() {
    }

    public Orders(int cartId, List<Entry> entryList, Double total, Address deliveryAddress, Address billingAddress, Payment payment, String email) {
        super(cartId, entryList, total, deliveryAddress, billingAddress, payment, email);
    }

    public void cloneCart(Cart cart) {
        this.setCartId(cart.getCartId());
        this.setBillingAddress(cart.getBillingAddress());
        this.setDeliveryAddress(cart.getDeliveryAddress());
        this.setEmail(cart.getEmail());
        this.setOrdersKey(cart.getOrdersKey());
        this.setPayment(cart.getPayment());
        this.setStatus(cart.getStatus());
    }
}
