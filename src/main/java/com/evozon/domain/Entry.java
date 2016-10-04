package com.evozon.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Entry {

    @Column(nullable = false)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entryId;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @Column
    private String productCode;

    @Column
    private String productName;

    @Column
    private Double productPrice;

    @Column
    private Integer quantity;

    @Column
    private Double subTotal;


    public Entry(){

    }

    public Entry(Cart cart, Product product, Integer quantity, Double subTotal) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public Entry(Integer entryId, Cart cart, Product product, Integer quantity, Double subTotal) {
        this.entryId = entryId;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    public Cart getCart() {

        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="productId", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "entryId=" + entryId +
                ", product=" + product.getName() +
                ", quantity=" + quantity +
                '}';
    }
}
