package com.fatechjsc.productservice.command.aggregate;

import com.fatechjsc.productservice.command.command.product.CreateProductCommand;
import com.fatechjsc.productservice.command.event.product.ProductCreateEvent;
import com.fatechjsc.productservice.utils.EStatusProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private String description;
    private Float price;
    private String thumbnail;
    private EStatusProduct isActive;

    public ProductAggregate(){

    }
    @CommandHandler
    public ProductAggregate(CreateProductCommand command) throws Exception{

        ProductCreateEvent event = new ProductCreateEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void On(ProductCreateEvent event){
        this.productId = event.getProductId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.isActive = event.getIsActive();
        this.price = event.getPrice();
        this.thumbnail = event.getThumbnail();
    }
}
