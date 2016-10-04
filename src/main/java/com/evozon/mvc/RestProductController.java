package com.evozon.mvc;

import com.evozon.domain.Category;
import com.evozon.domain.Product;
import com.evozon.domain.dtos.ProductDTO;
import com.evozon.service.CategoryService;
import com.evozon.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

//
//@Controller
//@RequestMapping(value = "/rest/product")
@RestController(value = "/rest/product")
public class RestProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public  List<ProductDTO> getAllProducts(HttpServletResponse response) {

        List<ProductDTO> products = productService.getAllDTOProducts();
        if (products.size() == 0) {
            response.setStatus(404);
        }else {
            response.setStatus(200);
        }
        return products;

    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public String getProduct(@PathVariable int productId, HttpServletResponse response) {

        Product product = productService.getProductById(productId);
        if (product != null) {
            ProductDTO productDTO = productService.populateProductDTO(product);
            String jsonProduct = new Gson().toJson(productDTO);
            response.setStatus(200);
            return jsonProduct;
        }
        response.setStatus(404);
        return new Gson().toJson(" The product with id " + productId + " does not exists!");
    }


    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteProduct(@PathVariable int productId, HttpServletResponse response) {

        Product product = productService.getProductById(productId);
        if (product != null) {
            response.setStatus(200);
            productService.deleteProduct(productId);
            return "The product with id " + productId + " have been deleted !";
        }
        response.setStatus(404);
        return "The product with id " + productId + " does not exists !";
    }


    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public String updateProduct(@RequestBody JsonObject jsonProduct, HttpServletResponse response) {
        int idCategory = Integer.parseInt(jsonProduct.get("idCategory").toString());
        Category cat = categoryService.getCategoryById(idCategory);
        Product product = new Gson().fromJson(jsonProduct, Product.class);
        product.setCategory(cat);
        Product searchedProduct = productService.getProductByCode(product.getCode());
        product.setProductId(searchedProduct.getProductId());
        Category searchedCategory = categoryService.getCategoryById(idCategory);
        if (searchedCategory != null) {
            if (searchedProduct != null) {
                if(product.getPrice()>=0&&product.getStockLevel()>=0) {
                    response.setStatus(200);
                    productService.updateProduct(product);
                    return " The product with code " + product.getCode() + " have been updated!";
                }
                response.setStatus(400);
                return " The product price and the stockLevel must be positive !";
            }
            response.setStatus(400);
            return " The product with code " + product.getCode() + " does not exists!";
        }
        response.setStatus(404);
        return "The category with this id does not exists!";
    }


    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String addProduct(@RequestBody JsonObject jsonProduct, HttpServletResponse response) {

        int idCategory = Integer.parseInt(jsonProduct.get("idCategory").toString());
        Category cat = categoryService.getCategoryById(idCategory);
        Product product = new Gson().fromJson(jsonProduct, Product.class);
        product.setCategory(cat);
        Product searchedProduct = productService.getProductByCode(product.getCode());
        Category searchedCategory = categoryService.getCategoryById(idCategory);
        if (searchedCategory != null) {
            if (searchedProduct == null) {
                if(product.getPrice()>=0&&product.getStockLevel()>=0) {
                    response.setStatus(200);
                    productService.addProduct(product);
                    return " The product was successfully added !";
                }
                response.setStatus(400);
                return " The product price and the stockLevel must be positive !";
            }
            response.setStatus(400);
            return " The product with code " + product.getCode() + " already exists!";
        }
        response.setStatus(404);
        return " The category with this id does not exists!";
    }

}
