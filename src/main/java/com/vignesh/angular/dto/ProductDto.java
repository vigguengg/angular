package com.vignesh.angular.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vignesh.angular.entities.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {

    private Long id;

    private String productName;

    private String description;
    private Long price;


    private byte[] byteimg;


    private Long categoryId;

    private MultipartFile img;
}
