package com.fatechjsc.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatechjsc.productservice.utils.EStatusProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String description;
    private Float price;
    private String thumbnail;

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("is_active")
    private EStatusProduct isActive;
}
