package com.evozon.service.job;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by mihaelarotarescu on 7/28/2016.
 */
@XmlRootElement
public class ProductXMLList {
    List<ProductXML> list;

    @XmlElement(type=ProductXML.class)
    public List<ProductXML> getList() {
        return list;
    }

    public void setList(List<ProductXML> list) {
        this.list = list;
    }
}
