package com.example.lab2product;

import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ModelAndView init(HttpSession session) {
        Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
        if (productList == null) {
            productList = new HashMap<String, Product>();
            session.setAttribute("productList", productList);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("productList", productList.values());
        return new ModelAndView("products", params);
    }

    @PostMapping("/add")
    public ModelAndView add(HttpSession session, @Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        System.out.println("error ----- " + bindingResult.hasErrors());
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("add_product");
            return mav;
        }

        Map<String, Object> params = new HashMap<>();
       if (product != null) {
           Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
           if (productList == null) {
               productList = new HashMap<String, Product>();
               session.setAttribute("productList", productList);
           }

           productList.put(product.getNumber(), product);
           params.put("productList", productList.values());
       }
        return new ModelAndView("redirect:products", params);
    }

    @PostMapping("/addproduct")
    public ModelAndView add_product() {
        Map<String, Object> params = new HashMap<>();
        params.put("product", new Product());
        return new ModelAndView("add_product", params);
    }

    @PostMapping("/delete")
    public ModelAndView removeproduct(@RequestParam("number") String number, HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        if (number != null) {
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }
            productList.remove(number);
            params.put("productList", productList.values());
        }
//      PRG Pattern redirect to products when delete(post method)
        return new ModelAndView("redirect:products", params);
    }

    @GetMapping("/addproduct")
    public ModelAndView get_product() {
        return new ModelAndView("add_product");
    }

}
