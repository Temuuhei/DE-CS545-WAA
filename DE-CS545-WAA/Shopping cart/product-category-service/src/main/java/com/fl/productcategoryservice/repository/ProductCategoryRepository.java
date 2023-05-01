package com.fl.productcategoryservice.repository;


import com.fl.productcategoryservice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

//    @Query("{name :  ?0}")
//    ProductCategory findByName(String name);
}
