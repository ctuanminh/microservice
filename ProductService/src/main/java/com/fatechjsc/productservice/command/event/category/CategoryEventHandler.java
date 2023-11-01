package com.fatechjsc.productservice.command.event.category;

import com.fatechjsc.productservice.model.Category;
import com.fatechjsc.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryEventHandler {

    private final CategoryRepository repository;

    @EventHandler
    public void on(CategoryCreateEvent event){
        var category = Category.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .build();
        repository.save(category);
    }
}
