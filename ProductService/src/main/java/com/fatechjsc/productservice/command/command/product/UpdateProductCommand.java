package com.fatechjsc.productservice.command.command.product;

import com.fatechjsc.productservice.utils.EStatusProduct;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateProductCommand {
    @TargetAggregateIdentifier
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String thumbnail;
    private EStatusProduct IsActive;
}
