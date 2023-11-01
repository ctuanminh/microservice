package com.fatechjsc.productservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatechjsc.productservice.utils.EStatusProduct;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @Size(min = 2, message = "Length name must be 2 characters")
    private String name;
    private String description;
    private Float price;
    @NotNull(message = "Thumbnail cannot empty")
    private String thumbnail;

    @JsonProperty("category_id")
    @NotNull(message = "category_id cannot empty")
    private String categoryId;

    @JsonProperty("is_active")
    private EStatusProduct isActive;
}
