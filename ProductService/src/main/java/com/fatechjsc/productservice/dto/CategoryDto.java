package com.fatechjsc.productservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private String id;
    @Size(min=2, message = "Length name must be 2 characters")
    private String name;
    private String description;
}
