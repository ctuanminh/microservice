package com.fatechjsc.productservice.command.aggregate;

import com.fatechjsc.productservice.command.command.category.CreateCategoryCommand;
import com.fatechjsc.productservice.command.event.category.CategoryCreateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public CategoryAggregate(){}

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command){
        var event = CategoryCreateEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .description(command.getDescription())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(CategoryCreateEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }

}
