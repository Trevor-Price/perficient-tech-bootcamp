package com.perficient.techbootcamp.ecommerce.dto.mapper;

import com.perficient.techbootcamp.ecommerce.dto.response.ProductDto;
import com.perficient.techbootcamp.ecommerce.entity.Product;

public class ProductDtoMapper {
    
    public static ProductDto toProductDto(Product product){
        return new ProductDto()
            .setId(product.getProductId())
            .setDescription(product.getDescription())
            .setBrand(product.getBrand().getName())
            .setPrice(product.getPrice())
            .setQuantityAvailable(product.getQuantityAvailable());
    }
    
}
