package com.evozon.service.job;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mihaelarotarescu on 7/28/2016.
 */
@Component
@XmlRootElement(name="ProductXML")
public class ProductXML {
    int productId;
    double price;

    @XmlElement
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    @XmlElement
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
