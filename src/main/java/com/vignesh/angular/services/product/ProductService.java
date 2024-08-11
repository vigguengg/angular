package com.vignesh.angular.services.product;

import com.vignesh.angular.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException, Exception;

    public List<ProductDto> getAllProducts();
}
