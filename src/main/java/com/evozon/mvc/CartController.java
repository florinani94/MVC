package com.evozon.mvc;

import com.evozon.domain.Entry;
import com.evozon.domain.Product;
import com.evozon.domain.dtos.MiniCartDTO;
import com.evozon.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mateimihai on 7/21/2016.
 */

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value="/entries", method = RequestMethod.POST)
    @ResponseBody
    public MiniCartDTO getCartProducts(Model model, @RequestParam("cartId") Integer cartId){
        MiniCartDTO minicart = cartService.getEntriesFromCart(cartId);

        return minicart;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public String viewDataFromCart(Model model, @RequestParam("cartId") Integer cartId){
        List<Entry> entries = cartService.getAllEntriesFromCart(cartId);

        List<String> messages = new ArrayList<String>();
        for (Entry e : entries) {
            if (e.getProduct().getStockLevel() == 0) {
                messages.add("The product " + e.getProductName().toUpperCase() + " is currently out of stock!");
                cartService.editEntry(e.getEntryId(), cartId, 0);
            }
        }

        model.addAttribute("entries", entries);
        model.addAttribute("total", cartService.getCartById(cartId).getTotal());
        model.addAttribute("stockErrorMessage", messages);
        return "viewCart";
    }

    @RequestMapping(value="/removeFromCart", method = RequestMethod.POST)
    public String removeDataFromCart(@RequestParam(value = "entryId") int entryId, @RequestParam(value = "cartId") int cartId){
        cartService.deleteEntryFromCart(entryId, cartId);
        return "viewCart";
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    @ResponseBody
    public String addToCart(@RequestParam String productId, @RequestParam String cartId, @RequestParam String quantity){
        String message;
        message=cartService.addProductToCart(Integer.parseInt(productId),Integer.parseInt(cartId),Integer.parseInt(quantity));
        return message;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editQuantity(@RequestParam(value = "entryId") int entryId, @RequestParam(value = "newQuantity") int quantity, @RequestParam(value = "cartId") int cartId){
        cartService.editEntry(entryId, cartId, quantity);
        return "viewCart";
    }

    @RequestMapping(value = "/getProductsNumber", method = RequestMethod.GET)
    @ResponseBody
    public int getCartProductsNumber(@RequestParam(value = "cartId") Integer cartId){
        return cartService.getNumberOfProductsInCart(cartId);
    }

}
