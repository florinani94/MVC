package com.evozon.service;

import com.evozon.dao.CartDAO;
import com.evozon.dao.OrderDAO;
import com.evozon.dao.ProductDAO;
import com.evozon.domain.Cart;
import com.evozon.domain.Entry;
import com.evozon.domain.Product;
import com.evozon.domain.*;
import com.evozon.domain.dtos.EntryDTO;
import com.evozon.domain.dtos.MiniCartDTO;
import com.evozon.domain.dtos.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladblana on 19/07/2016.
 */
@Service
public class CartService {
    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ServletContext servletContext;

    public void saveCart(Cart cart){
        cartDAO.addCart(cart);
    }

    public void updateCart(Integer cartId){
        Cart cart=cartDAO.getCartById(cartId);
        cartDAO.updateCart(cart);
    }

    public void deleteCart(Integer cartId){
        cartDAO.deleteCart(cartId);
    }

    public String editEntry(Integer entryId,Integer cartId,Integer quantity){
        if(quantity<0)return "Invalid quantity!";
        String status = "Edit successful!";
        Entry entry=cartDAO.getEntryById(entryId);
        entry.setQuantity(quantity);
        if(entry.getQuantity()==0){
            cartDAO.deleteEntryFromCart(entry.getEntryId());
            cartDAO.computeTotalForCart(cartId);
        }
        else {
            if (entry.getProduct().getStockLevel() < entry.getQuantity()) {
                entry.setQuantity(entry.getProduct().getStockLevel());
                status = "Insufficient stock. Maximum available quantity added in cart.";
            }
            cartDAO.updateEntry(entry);
            Double subTotal = cartDAO.computeSubTotalForEntry(entry.getEntryId(), cartId);
            cartDAO.updateSubTotalForEntry(subTotal, entry.getEntryId(), cartId);
            cartDAO.computeTotalForCart(cartId);
        }
        return status;
    }
    public String addProductToCart(Integer productId,Integer cartId,Integer quantity) {
        if(quantity<0)return "Invalid quantity!";
        String status = "";
        List<Entry> entryList=cartDAO.getEntryForAdding(productId,cartId);
        if(entryList.size()>0){
            for(Entry e:entryList){
                if(quantity==0){
                    cartDAO.deleteEntryFromCart(e.getEntryId());
                    status="Product successfully deleted from cart!";
                }else {
                    if (e.getProduct().getStockLevel() >= e.getQuantity() + quantity) {
                        e.setQuantity(e.getQuantity() + quantity);
                        status = "Product successfully added to cart with quantity: " + quantity;
                    } else {
                        e.setQuantity(e.getProduct().getStockLevel());
                        status = "Insufficient stock. Maximum available quantity added in cart.";
                    }
                    cartDAO.updateEntry(e);
                    Double subTotal = cartDAO.computeSubTotalForEntry(e.getEntryId(), cartId);
                    cartDAO.updateSubTotalForEntry(subTotal, e.getEntryId(), cartId);
                }
                cartDAO.computeTotalForCart(cartId);
            }
        }
        else{
            Cart cart=cartDAO.getCartById(cartId);
            Product product=productDAO.getProductById(productId);
            if(product.getStockLevel()==0){
                return "Product is out of stock!";
            }
            Entry entry=cartDAO.addEntryToCart(product,cart);
            cartDAO.updateEntryDetails(entry);
            status=addProductToCart(productId,cartId,quantity);
        }

        List<Cart> cartList=cartDAO.getAllCarts();
        for(Cart c:cartList){
            if(c.getTotal()==null){
                cartDAO.deleteCart(c.getCartId());
            }
        }
        return status;
    }

    public void deleteEntryFromCart(Integer entryId, Integer cartId) {
        cartDAO.deleteEntryFromCart(entryId);
        cartDAO.computeTotalForCart(cartId);
    }

    public void updateAddress(Cart cart) {
        cartDAO.updateAddress(cart);
    }

    public MiniCartDTO getEntriesFromCart(Integer cartId){
        Cart cartModel = cartDAO.getCartById(cartId);

        MiniCartDTO miniCart = new MiniCartDTO();
        miniCart.setTotal(cartModel.getTotal());

        List<Entry> entryList = cartModel.getEntryList();
        ArrayList<EntryDTO> entryDTOList = new ArrayList<>();
        for(Entry entryModel : entryList) {
            EntryDTO entryDTO = new EntryDTO();
            entryDTO.setId(entryModel.getEntryId());
            entryDTO.setName(entryModel.getProductName());
            entryDTO.setPrice(entryModel.getProductPrice());
            entryDTO.setQuantity(entryModel.getQuantity());
            entryDTO.setSubtotal(entryModel.getSubTotal());

            entryDTOList.add(entryDTO);
        }

        miniCart.setEntries(entryDTOList);

        return miniCart;
    }

    public List<Entry> getAllEntriesFromCart(Integer cartId) {
        return cartDAO.getAllEntriesFromCart(cartId);
    }

    public Cart getCartById(int id) {
        return cartDAO.getCartById(id);
    }

    public Integer getNumberOfProductsInCart(Integer cartId)
    {
        Integer result = 0;
        List<Entry> entryList=cartDAO.getAllEntriesFromCart(cartId);
        for(Entry e:entryList){
            result+=e.getQuantity();
        }
        return result;
    }

    public void getDataFromOrderds(OrdersDTO order, Cart cart) {
        Address deliveryAddress = new Address();
        Address billingAddress = new Address();
        Payment payment = new Payment();
        deliveryAddress.setCity(order.getDeliveryCity());
        deliveryAddress.setNumber(order.getDeliveryNumber());
        deliveryAddress.setStreet(order.getDeliveryStreet());
        deliveryAddress.setPhone(order.getDeliveryPhone());
        billingAddress.setCity(order.getBillingCity());
        billingAddress.setNumber(order.getBillingNumber());
        billingAddress.setPhone(order.getBillingPhone());
        billingAddress.setStreet(order.getBillingStreet());
        payment.setPaymentMethod(order.getPaymentMethod());
        payment.setCardNumber(order.getCardNumber());
        payment.setCardCode(order.getCardCode());
        cart.setDeliveryAddress(deliveryAddress);
        cart.setBillingAddress(billingAddress);
        cart.setPayment(payment);
        cart.setEmail(order.getEmail());
    }

    @Transactional
    public Cart createCart() {
        Cart cart = new Cart();
        this.saveCart(cart);

        return cart;
    }
}
