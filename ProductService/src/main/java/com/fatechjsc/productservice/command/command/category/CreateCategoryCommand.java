package com.fatechjsc.productservice.command.command.category;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String description;
}
