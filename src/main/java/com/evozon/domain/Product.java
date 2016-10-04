package com.evozon.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Column(nullable = false)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, length = 8)
    private String code;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(targetEntity=Entry.class,mappedBy="product",fetch=FetchType.EAGER)
    private Set<Entry> entrySet;

    @Column
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stockLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategory")
    private Category category;

    @Column
    private String imageURL;

    public Product() {
    }

    public Product(String code, String name, String description, Double price, Integer stockLevel) {

        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
    }


    public Product(String code, String name, String description, Double price, Integer stockLevel,Category category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockLevel = stockLevel;
        this.category=category;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(Integer stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Entry> getEntrySet() {
        return entrySet;
    }

    public void setEntrySet(Set<Entry> entrySet) {
        this.entrySet = entrySet;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", entrySet=" + entrySet +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockLevel=" + stockLevel +
                ", category=" + category.getName() +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
