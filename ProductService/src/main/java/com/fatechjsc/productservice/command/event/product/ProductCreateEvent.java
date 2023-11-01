package com.fatechjsc.productservice.command.event.product;

import com.fatechjsc.productservice.utils.EStatusProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateEvent {
    private String productId;
    private String name;
    private String description;
    private Float price;
    private String categoryId;
    private EStatusProduct isActive;
    private String thumbnail;

}
