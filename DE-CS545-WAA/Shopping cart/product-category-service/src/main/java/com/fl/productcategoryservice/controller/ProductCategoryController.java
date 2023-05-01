package com.fl.productcategoryservice.controller;


import com.fl.productcategoryservice.entity.ProductCategory;
import com.fl.productcategoryservice.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;


    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody ProductCategory category) {
       if (category.getName() == null) {
           return new ResponseEntity<>(new CategoryError("Product category name is not null!"), HttpStatus.FORBIDDEN);
       }
       productCategoryService.saveCategory(category);
       return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<?> findByCategId(@PathVariable Long category_id) {
        ProductCategory category = productCategoryService.findByCategoryId(category_id);
        if (category == null) {
            return new ResponseEntity<>(new CategoryError("Product category does not exist"), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


}
