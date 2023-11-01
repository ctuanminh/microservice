package com.fatechjsc.productservice.command.event.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeleteEvent {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String password;
    private Boolean isActive;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long facebook_account_id;
    private Long google_account_id;
}
