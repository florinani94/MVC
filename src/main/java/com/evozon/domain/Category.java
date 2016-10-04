package com.evozon.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    private Integer id;

    @Column(nullable  = false, length = 30)
    private String name;

    @Column(nullable  = false, length = 30)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<Product>();


    public Category() {
    }

    public Category(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category(Integer id, String name, Set<Product> products, String description) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Product> getProducts() {return products;}

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
