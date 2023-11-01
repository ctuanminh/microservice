package com.fatechjsc.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends BaseEntity {
    private static final int MAXIMUM_IMAGE_PER_PRODUCT = 6;

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url", nullable = false, length = 300)
    private String imageUrl;
}
