package com.fatechjsc.productservice.model;

import com.fatechjsc.productservice.utils.EStatusProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "products")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity{
    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "name", nullable = false)
    @Size(min = 2, message = "Name length must be 2 characters")
    private String name;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "is_active")
    @Enumerated(EnumType.STRING)
    private EStatusProduct IsActive;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
