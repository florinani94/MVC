package com.evozon.service.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
/**
 * Created by mihaelarotarescu on 7/28/2016.
 */
@Service
public class XMLConvertService {

    @Autowired
    private ServletContext servletContext;

    public ArrayList<ProductXML> readFromTxtFile(String fileName) {

        ArrayList<ProductXML> list = new ArrayList<>();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(servletContext.getRealPath("/resources/"+fileName+".txt")));

            String fileRead = br.readLine();

            while (fileRead != null)
            {

                String[] tokenize = fileRead.split(",");

                int id = Integer.parseInt(tokenize[0]);
                double price = Double.parseDouble(tokenize[1]);

                ProductXML productXML = new ProductXML();
                productXML.setProductId(id);
                productXML.setPrice(price);

                list.add(productXML);

                fileRead = br.readLine();
            }

            br.close();

            return list;
        }

        catch (FileNotFoundException e)
        {
           e.getMessage();
        }

        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }

    public void createXML(ArrayList<ProductXML> list, String fileName){

        ProductXMLList productList = new ProductXMLList();
        productList.setList(list);

        try {

            File file = new File(fileName+".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductXMLList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(productList, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductXML> readXML(String fileName){
        try {

            File file = new File(fileName+".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductXMLList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ProductXMLList list = (ProductXMLList) jaxbUnmarshaller.unmarshal(file);

            return (ArrayList<ProductXML>) list.getList();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
