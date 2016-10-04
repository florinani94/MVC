package com.evozon.mvc;


import com.evozon.dao.CartDAO;
import com.evozon.domain.*;
import com.evozon.domain.dtos.OrdersDTO;
import com.evozon.domain.*;
import com.evozon.mvc.validator.OrderValidator;
import com.evozon.service.*;
import com.evozon.util.CreateUrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.EntityManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(value = "products")
public class CustomerProductController {

    public static final int MAX_PRODUCTS_PER_PAGE= 12;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SendOrderMail orderManager;

    @Autowired
    private CreateUrlUtils createUrlUtils;

    @Autowired
    private OrderValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(@RequestParam(value = "sortValue", defaultValue = "none") String sortValue,
                                 Model model, @RequestParam(value = "page", defaultValue = "1") Integer startPageIndex,
                                 @RequestParam(value = "category", defaultValue = "") Integer[] categoriesArray) {


        List<Integer> categoriesList;
        if(categoriesArray.length != 0) {
            categoriesList = new ArrayList<>(Arrays.asList(categoriesArray));
            for (Integer categoryValue : categoriesArray) {
            }
        }
        else {
            categoriesList = new ArrayList<>();
        }
        model.addAttribute("products", productService.getSortedProducts(sortValue,startPageIndex, MAX_PRODUCTS_PER_PAGE,categoriesList));
        model.addAttribute("productSize", productService.getSize(categoriesList));
        model.addAttribute("currentPage", startPageIndex);
        model.addAttribute("sortValue", sortValue);
        model.addAttribute("categories", categoryService.getCategoriesWithAtLeastOneProduct());
        model.addAttribute("selectedCategoriesUrl", createUrlUtils.CreateUrlForFilter(categoriesArray));
        return "customerViewProducts";
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String goToCheckout(@RequestParam("cartId") int cartId, Model model) {
        Cart cart = new Cart();
        if (cartService.getCartById(cartId) != null) {
            cart = cartService.getCartById(cartId);
        }
        model.addAttribute("cart", cart);
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.getDataFromCart(cart);
        model.addAttribute("order", ordersDTO);
        return "customerCartCheckout";
    }



    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public String checkout(Model model, @ModelAttribute("order") OrdersDTO order, BindingResult data) {
        model.addAttribute("order", order);
        validator.validate(order, data);
        if(data.hasErrors()){
            return "customerCartCheckout";
        }
        Cart cart = cartService.getCartById(order.getCartId());
        String keyUrl = UUID.randomUUID().toString();
        cart.setOrdersKey(keyUrl);
        cart.setStatus("processing");
        cartService.getDataFromOrderds(order, cart);
        cartService.updateAddress(cart);
        model.addAttribute("cart", cart);
        return "customerOrderPlaced";
    }

    @RequestMapping(value = "/customerOrderPlaced", method = RequestMethod.GET)
    public String orderPlaced(@RequestParam(value = "cartId") Integer cartId, HttpServletResponse httpServletResponse , HttpServletRequest httpServletRequest) {
        Cart cart = cartService.getCartById(cartId);

        List<Entry> entries = cartService.getAllEntriesFromCart(cartId);
        for (Entry e : entries) {
            Product p = e.getProduct();
            Integer newProductStock = p.getStockLevel() - e.getQuantity();
            p.setStockLevel(newProductStock);
            productService.updateProduct(p);
        }

        Orders orderPlaced = new Orders();
        orderPlaced.cloneCart(cart);
        orderService.addOrder(orderPlaced);
        orderManager.sendOrderPlacementMail(orderPlaced.getEmail(), "Iulia", orderPlaced.getOrdersKey(), "/mvc/products/details");
        Cart newCart = cartService.createCart();

        //todo: refactor the code - as it's currently duplicated

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("cart", newCart);

        Cookie newCookie = new Cookie("cartId", String.format("%s", newCart.getCartId()));
        newCookie.setPath("/mvc");

        newCookie.setMaxAge(43200); //The time the cookie can be eaten is 12 hours
        httpServletResponse.addCookie(newCookie);

        return "redirect:/products";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String showProductDetails(Model model, @RequestParam String productId){
        model.addAttribute("theProduct", productService.getProductById(Integer.parseInt(productId)));
        return "productDetailsPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteAll(@RequestParam(value = "sortValue", defaultValue = "none") String sortValue,
                            Model model, @RequestParam(value = "page", defaultValue = "1") Integer startPageIndex,
                            @RequestParam(value = "selectedCategoriesArray[]") List<Integer> categoriesArray) {


        return "customerViewProducts";
    }

    /* Order details page */

    @RequestMapping(value = "details", method = RequestMethod.GET)
    public String getOrderDetailsPage(@RequestParam("orderKey") String orderKey, Model model) {
        Cart orderDetails = orderService.getOrderByKey(orderKey);
        System.out.println("This is cart "+ orderDetails.toString());
        List<Entry> entries = orderService.getAllEntries(orderDetails.getCartId());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("entries", entries);

        return "orderDetails";
    }
}