package com.fl.productcategoryservice.service;


import com.fl.productcategoryservice.entity.ProductCategory;
import com.fl.productcategoryservice.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public ProductCategory saveCategory(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    public ProductCategory findByCategoryId(Long categ_id) {
        System.out.println("Categ in Service : " +  productCategoryRepository.getReferenceById(categ_id).getName());
        return productCategoryRepository.getReferenceById(categ_id);
    }
}
