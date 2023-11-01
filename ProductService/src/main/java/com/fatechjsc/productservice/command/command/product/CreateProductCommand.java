package com.fatechjsc.productservice.command.command.product;

import com.fatechjsc.productservice.utils.EStatusProduct;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String productId;
    private String name;
    private String description;
    private Float price;
    private String thumbnail;
    private String categoryId;
    private EStatusProduct IsActive;
}
