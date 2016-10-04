package com.evozon.service;

import com.evozon.dao.CategoryDAO;
import com.evozon.dao.ProductDAO;
import com.evozon.domain.Category;
import com.evozon.domain.Product;
import com.evozon.domain.dtos.ProductDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;


    @Autowired
    private ServletContext servletContext;

    public void addProduct(Product product) {
        this.productDAO.addProduct(product);
    }


    public void deleteProduct(Integer productId) { this.productDAO.deleteProduct(productId);}


    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }


    public boolean validateProduct(Product product) {
        String voidString="";
        if ( (!voidString.equals(product.getCode()) && (!voidString.equals(product.getName()) && (0 <= product.getPrice()) && (0 <= product.getStockLevel()))) ) {
            return true;
        }
        return false;
    }


    public void importFromFile(String filename) {
        productDAO.importFromFile(filename);
    }


    public Product getProductById(Integer id){
       return  productDAO.getProductById(id);
    }

    public Product getProductByCode(String code){
        return  productDAO.getProductByCode(code);
    }

    public List<Product>  getParticularProducts(List<Integer> prodArray){
        List<Product> products = productDAO.getParticularProducts(prodArray);
        return products;
     }

    public void exportToCSV(String fileName, List<Product> products) {


        FileWriter writer = null;


            try {
                writer = new FileWriter(fileName + ".csv");
                for (Product product : products) {
                    writer.append(product.getCode());
                    writer.append(",");
                    writer.append(product.getName());
                    writer.append(",");
                    writer.append(product.getDescription());
                    writer.append(",");
                    writer.append(String.valueOf(product.getPrice()));
                    writer.append(",");
                    writer.append(String.valueOf(product.getStockLevel()));
                    writer.append(",");
                    writer.append(String.valueOf(categoryDAO.getCategoryById(product.getCategory().getId()).getId()));
                    writer.append(",");
                    writer.append(product.getImageURL());
                    writer.append("\n");
                }

                writer.flush();
                writer.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
            finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    public List<Product> validateExport(String fileName) {

        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        List<Product> list = new ArrayList<Product>();

        try {

            File f = new File(fileName + ".csv");
            if(f.exists()) {
                br = new BufferedReader(new FileReader((fileName + ".csv")));
                while ((line = br.readLine()) != null) {

                    String[] product = line.split(csvSplitBy);
                    Product newProduct = new Product();
                    newProduct.setCode(product[0]);
                    newProduct.setName(product[1]);
                    newProduct.setDescription(product[2]);
                    newProduct.setPrice(Double.valueOf(product[3]));
                    newProduct.setStockLevel(Integer.valueOf(product[4]));
                    Integer categoryId = Integer.valueOf(product[5]);
                    Category category = null;

                    try {
                        category = categoryDAO.getCategoryById(categoryId);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    newProduct.setCategory(category);
                    newProduct.setImageURL(product[6]);

                    list.add(newProduct);

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }


    public List<Product> getProductsForPage(int startPageIndex, int recordsPerPage){

        if(startPageIndex<=0){
            return null;
        }
        return productDAO.getProductsForPage(startPageIndex,recordsPerPage);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public int getSize(List<Integer> categoriesListIds){
        Integer maxSize=productDAO.getAllProducts().size();
        Integer categoriesSize=0;
        if (categoriesListIds.size()>0) {
            for (Integer categoryId : categoriesListIds) {
                categoriesSize += productDAO.getProductsByCategory(categoryId).size();
            }
            return categoriesSize;
        }
        else {
            return maxSize;
        }

    }

    public List<Product> getSortedProducts(String option, Integer startPageIndex, Integer recordsPerPage,List<Integer> selectedCategoriesIds){
        String filterByCategories;
        if (selectedCategoriesIds.size()==0)
        {
            filterByCategories="";
        }
        else {
            filterByCategories=" WHERE P.category.id in (:categoriesIds) ";
        }

            switch (option) {
                case "sortpriceupdown":
                    return productDAO.getSortedProducts("FROM Product P" + filterByCategories + " ORDER BY P.price", startPageIndex, recordsPerPage, selectedCategoriesIds);
                case "sortpricedownup":
                    return productDAO.getSortedProducts("FROM Product P" + filterByCategories + " ORDER BY P.price DESC", startPageIndex, recordsPerPage, selectedCategoriesIds);
                case "sortnameaz":
                    return productDAO.getSortedProducts("FROM Product P" + filterByCategories + " ORDER BY P.name", startPageIndex, recordsPerPage, selectedCategoriesIds);
                case "sortnameza":
                    return productDAO.getSortedProducts("FROM Product P " + filterByCategories + " ORDER BY P.name DESC", startPageIndex, recordsPerPage, selectedCategoriesIds);
                default:
                    if (selectedCategoriesIds.size() == 0) {
                        return productDAO.getProductsForPage(startPageIndex, recordsPerPage);
                    } else {
                        return productDAO.getProductsFilteredByCategories(startPageIndex, recordsPerPage, selectedCategoriesIds);
                    }
            }
    }

    /* save image to local */
    public void saveImage(String filename, MultipartFile image) {
        File file = new File(servletContext.getRealPath("/resources/productImages" + "/" + filename));

        try {
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* validation for .jpg */
    public boolean validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            return false;
        }

        return true;
    }

    /* delete a image based on the id */
    public void deleteImage(int id) {
        File image = new File(servletContext.getRealPath(productDAO.getProductById(id).getImageURL()));

        image.delete();
    }

    /* execute the image save operation */
    public Product doImageSaveOperation(Product product,  MultipartFile image) {
        if((!image.isEmpty()) && (this.validateImage(image) == true)) {
            this.saveImage(product.getCode() + ".jpg", image);
            String imageURL = "/resources/productImages/" + product.getCode() + ".jpg";
            product.setImageURL(imageURL);
        } else {
            String defaultImagePath = "/resources/productImages/default@product.jpg";
            MultipartFile defaultImage = null;

            try {
                defaultImage = this.getImageForProduct(product.getCode(), defaultImagePath);
                this.doImageSaveOperation(product, defaultImage);
            } catch (Exception e) { /* Do something later */ }

        }

        return product;
    }

    /* get the defaul image */
    public MultipartFile getImageForProduct(String productCode, String path) throws Exception {
        File file = new File(servletContext.getRealPath(path));
        MultipartFile image = null;

        FileInputStream input = new FileInputStream(file);
        image = new MockMultipartFile(productCode + ".jpg", file.getName(), "image/jpeg", IOUtils.toByteArray(input));

        return image;
    }

    public List<Product> getProductsByCategory(int categoryId){
        return  productDAO.getProductsByCategory(categoryId);
    }

    public ProductDTO populateProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setCode(product.getCode());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockLevel(product.getStockLevel());
        if(product.getCategory() != null){
        productDTO.setIdCategory(product.getCategory().getId());
        }else{
            productDTO.setIdCategory(0);
        }
        productDTO.setImageURL(product.getImageURL());
        return productDTO;
    }


    public List<ProductDTO> getAllDTOProducts(){
        List<Product> products = getAllProducts();
        List<ProductDTO> DTOproducts = new ArrayList<>();
        for(Product product: products){
            ProductDTO productDTO = populateProductDTO(product);
            DTOproducts.add(productDTO);
        }
        return DTOproducts;
    }

    public void updatePrice(int productId, double price){
        Product product = this.getProductById(productId);
        product.setPrice(price);
        this.updateProduct(product);
    }

    public List<Product> getProductsByCategories(Integer startPageIndex, int maxProductsPerPage, List<Integer> categoriesList) {
        if(startPageIndex<=0){
            return null;
        }
        return productDAO.getProductsFilteredByCategories(startPageIndex,maxProductsPerPage,categoriesList);
    }


    /* import form file V2 */
    public void importFromCSV(MultipartFile filename) {
        try {
            InputStream inputStream = filename.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String input;
            while((input = br.readLine()) != null) {
                String[] productDetails = input.split(",");
                Product product = new Product();
                product.setCode(productDetails[0]);
                product.setDescription(productDetails[1]);
                product.setName(productDetails[2]);
                product.setPrice(Double.parseDouble(productDetails[3]));
                product.setStockLevel(Integer.parseInt(productDetails[4]));

                Category category = categoryDAO.getCategoryById(Integer.parseInt(productDetails[5]));
                product.setCategory(category);

                try {
                    MultipartFile image = this.getImageForProduct(product.getCode(),productDetails[6]);
                    productDAO.addProduct(this.doImageSaveOperation(product, image));
                } catch (Exception e) {
                    String defaultImagePath = "/resources/productImages/default@product.jpg";
                    MultipartFile image = this.getImageForProduct(product.getCode(), defaultImagePath);
                    productDAO.addProduct(this.doImageSaveOperation(product, image));
                }
            }

        } catch (Exception e) { /* do something later */ }
    }
}

