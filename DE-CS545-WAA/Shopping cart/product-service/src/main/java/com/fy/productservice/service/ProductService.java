package com.fy.productservice.service;

import com.fy.productservice.VO.ProductCategory;
import com.fy.productservice.VO.ResponseTemplateVO;
import com.fy.productservice.entity.Product;
import com.fy.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void save(Product product) {
        productRepository.save(product);
        System.out.println("Saving product ....");

    }

    public Product get_product(Long productId) {
        return productRepository.getReferenceById(productId);
    }

    public ResponseTemplateVO getProductWithCategory(Long productId) {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Product product = productRepository.getReferenceById(productId);
        ProductCategory category = restTemplate.getForObject("http://PRODUCT-CATEGORY-SERVICE/category/" + product.getCategoryId(),
                ProductCategory.class);

        responseTemplateVO.setProduct(product);
        responseTemplateVO.setProductCategory(category);

        return responseTemplateVO;

    }
}
