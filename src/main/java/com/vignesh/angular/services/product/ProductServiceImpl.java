package com.vignesh.angular.services.product;

import com.vignesh.angular.dto.ProductDto;
import com.vignesh.angular.entities.Product;
import com.vignesh.angular.repositories.CategoryRepository;
import com.vignesh.angular.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductDto addProduct(ProductDto productDto) throws Exception {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImg(productDto.getImg().getBytes());
        return productRepository.save(product).getDto();

    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(Product::getDto).toList();
    }
}
