package com.evozon.mvc.interceptor;

import com.evozon.domain.Cart;
import com.evozon.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartCookieInterceptor implements HandlerInterceptor {

    @Autowired
    private CartService cartService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies != null) {
            // flag = cookie exists or not
            Boolean flag = false;
            HttpSession session = httpServletRequest.getSession(true);


            for (Cookie cookie : cookies)
                if ("cartId".equals(cookie.getName())) {
                    flag = true;
                    //cookie exists but session not. Now we create a session
                    if (session.getAttribute("cart") == null){
                        Cart cart = cartService.getCartById(Integer.parseInt(cookie.getValue()));
                        session.setAttribute("cart", cart);


                    }
                }

            //the cookie was not found on request
            if (flag == false) {
                //1. Create a new Cart if cart not exist
                Cart newCart = new Cart();
                cartService.saveCart(newCart);

                //2. Create a new Cookie with the cartId
                Cookie newCookie = new Cookie("cartId", String.format("%s", newCart.getCartId()));
                newCookie.setMaxAge(43200); //The time the cookie can be eaten is 12 hours
                httpServletResponse.addCookie(newCookie);

                //3. Put Cart on user Session
                session.setAttribute("cart", newCart);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
