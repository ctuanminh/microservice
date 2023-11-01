package com.fatechjsc.productservice.command.command.product;

import com.fatechjsc.productservice.utils.EStatusProduct;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteProductCommand {
    @TargetAggregateIdentifier
    private Long id;
    private EStatusProduct IsActive;
}
