package com.fatechjsc.productservice.command.event.product;

import com.fatechjsc.productservice.model.Product;
import com.fatechjsc.productservice.model.User;
import com.fatechjsc.productservice.repository.CategoryRepository;
import com.fatechjsc.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductEventHandler {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    @EventHandler
    public void on(ProductCreateEvent event)
    {
        var category = categoryRepository.findById(event.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id=" + event.getCategoryId()));
        var product = Product.builder()
                .productId(event.getProductId())
                .name(event.getName())
                .description(event.getDescription())
                .IsActive(event.getIsActive())
                .category(category)
                .thumbnail(event.getThumbnail())
                .price(event.getPrice())
                .build();
        repository.save(product);
    }

    @EventHandler
    public void on(ProductUpdateEvent event){
        var product = repository.findById(event.getId())
                .orElseThrow(()->new IllegalArgumentException("User not found"));
        BeanUtils.copyProperties(event, product);
        repository.save(product);
    }

    @EventHandler
    public void on(ProductDeleteEvent event){
        repository.deleteById(event.getId());
    }
}
