package com.vignesh.angular.controller;

import com.vignesh.angular.dto.ProductDto;
import com.vignesh.angular.entities.Product;
import com.vignesh.angular.repositories.CategoryRepository;
import com.vignesh.angular.repositories.ProductRepository;
import com.vignesh.angular.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/add")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {


        System.out.println("inside product add" + productDto.getProductName());
        ProductDto productDto1 = null;
        try {
            productDto1 = productService.addProduct(productDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(productDto1);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<ProductDto> productDto1 = productService.getAllProducts();
        return ResponseEntity.ok(productDto1);
    }
}
