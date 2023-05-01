package com.fy.productservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    private Long categoryId;
    private String name;
    private String description;
}
