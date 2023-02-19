package com.example.lab2product;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CartController {


    // showcart
    @GetMapping("/showcart")
    public ModelAndView show_cart(HttpSession session, @RequestParam(value = "totalShoppingCart") String totalShoppingCart) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Product> carts = (Map<String, Product>) session.getAttribute("carts");
        params.put("carts", carts.values());
        params.put("totalShoppingCart", totalShoppingCart);
        return new ModelAndView("cart", params);

    }

    //  add to cart
    @PostMapping("/addtocart")
    public ModelAndView add_cart(HttpSession session, @ModelAttribute("product") Product product) {
        Map<String, Object> params = new HashMap<>();
        if (product != null) {
            Map<String, Product> carts = (Map<String, Product>) session.getAttribute("carts");
            if (carts == null) {
                carts = new HashMap<String, Product>();
                session.setAttribute("carts", carts);
            }

            Product existingProduct = carts.get(product.getNumber());
            System.out.println("1111 " + existingProduct);
            if (existingProduct != null) {
                existingProduct.increaseQty();
                System.out.println("added " + existingProduct);
            } else {
                product.setQty(1);
                System.out.println("TLL " + product.toString());
                carts.put(product.getNumber(), product);
            }
            params.put("carts", carts.values());
            params.put("totalShoppingCart",carts.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum());
        }
        return new ModelAndView("redirect:showcart", params);
//        return new ModelAndView("cart", params);
    }

    @PostMapping("/removefromcart")
    public ModelAndView remove_cart(HttpSession session, @RequestParam(value = "number") String number) {
        Map<String, Product> carts = (Map<String, Product>) session.getAttribute("carts");
        if (carts != null) {
            Product existedProduct = carts.get(number);
            if (existedProduct != null) {
                if (existedProduct.getQty() > 1) {
                    existedProduct.decreaseQty();
                } else {
                    carts.remove(number);
                }
            }
        }
        Map<String, Object> params = new HashMap<>();
        params.put("carts", carts.values());
        params.put("totalShoppingCart",carts.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum());
        return new ModelAndView("redirect:showcart", params);
//        return new ModelAndView("cart", params);
    }



}
