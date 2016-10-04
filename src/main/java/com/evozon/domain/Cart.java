package com.evozon.domain;
import org.hibernate.annotations.Parent;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * Created by vladblana on 19/07/2016.
 */

@Entity
@Table(name = "cart")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cart {

    @Column(nullable = false)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToMany(targetEntity=Entry.class,mappedBy="cart",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Entry> entryList;

    @Column
    private Double total;

    @Column
    private String ordersKey;

    @Column
    private String status;

    @Column
    @Email
    private String email;

    @AttributeOverrides({
            @AttributeOverride(name="paymentMethod",column=@Column(name="paymentMethod")),
            @AttributeOverride(name="cardNumber",column=@Column(name="cardNumber")),
            @AttributeOverride(name="cardCode",column=@Column(name="cardCode")),
    })
    @Embedded
    private Payment payment;

    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="deliveryStreet")),
            @AttributeOverride(name="number",column=@Column(name="deliveryNumber")),
            @AttributeOverride(name="city",column=@Column(name="deliveryCity")),
            @AttributeOverride(name="phone",column=@Column(name="deliveryPhone"))
    })
    @Embedded
    private Address deliveryAddress;

    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="billingStreet")),
            @AttributeOverride(name="number",column=@Column(name="billingNumber")),
            @AttributeOverride(name="city",column=@Column(name="billingCity")),
            @AttributeOverride(name="phone",column=@Column(name="billingPhone"))
    })
    @Embedded
    private Address billingAddress;

    public Cart(){

    }

    public Cart(int cartId, List<Entry> entryList, Double total, Address deliveryAddress, Address billingAddress, Payment payment, String email) {
        this.cartId = cartId;
        this.entryList = entryList;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.payment = payment;
        this.email = email;
    }

    public int getCartId() {

        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @OneToMany(targetEntity=Entry.class,mappedBy="cart",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    public List<Entry> getEntryList() {
        return entryList;
    }

    public void settEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", entryList=" + entryList +
                ", total=" + total +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public String getOrdersKey() {
        return ordersKey;
    }

    public void setOrdersKey(String ordersKey) {
        this.ordersKey = ordersKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
