package com.fatechjsc.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity{
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
