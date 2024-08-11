package com.vignesh.angular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vignesh.angular.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Lob
    private String description;
    private Long price;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDto getDto() {
        ProductDto dto = new ProductDto();
        dto.setId(id);
        dto.setByteimg(img);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setProductName(productName);
        dto.setCategoryId(category.getId());
        return dto;
    }

}
