package com.fatechjsc.productservice.command.event.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateEvent {
    private String id;
    private String name;
    private String description;
}
