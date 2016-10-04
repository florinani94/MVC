package com.evozon.service.job;

import com.evozon.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
/**
 * Created by mihaelarotarescu on 7/28/2016.
 */
public class UpdatePriceJob {

    private ProductService service;
    private XMLConvertService xmlService;

    public void runJob() {

        System.out.println("\n\n Current Time: "+new Date()+"\n\n");

        ArrayList<ProductXML> list = xmlService.readFromTxtFile("productList");
        xmlService.createXML(list,"product");
        for (ProductXML p : list)
        {
            System.out.println("ID: "+p.getProductId()+" Price: "+p.getPrice()+"\n");
        }

        ArrayList<ProductXML> products = xmlService.readXML("product");
        for(ProductXML p: products){
            int id = p.getProductId();
            double price = p.getPrice();
            service.updatePrice(id,price);
        }
    }
    public void setService(ProductService service) {
        this.service = service;
    }
    public ProductService getService() {
        return service;
    }
    public void setXmlService(XMLConvertService xmlService) {
        this.xmlService = xmlService;
    }
    public XMLConvertService getXmlService() {
        return xmlService;
    }
}
