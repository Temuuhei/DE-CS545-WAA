package com.fy.productservice.controller;

import com.fy.productservice.VO.ResponseTemplateVO;
import com.fy.productservice.entity.Product;
import com.fy.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> save_product(@RequestBody Product product) {
        if (product.getName() == null) {
            return new ResponseEntity<>(new ProductError("Product name is not null"), HttpStatus.FORBIDDEN);
        }

        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @GetMapping("/{productId}")
//    public ResponseEntity<?> get_product (@PathVariable Long productId) {
//        Product product = productService.get_product(productId);
//        if (product == null) {
//            return new ResponseEntity<>(new ProductError("Product ID : " + productId + " is not existed"), HttpStatus.FORBIDDEN);
//        }
//
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
    // get product with product category
    @GetMapping("/{productId}")
    public ResponseTemplateVO getProductWithCategory(@PathVariable  Long productId) {
        return productService.getProductWithCategory(productId);
    }
}
