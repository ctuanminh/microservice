package com.fatechjsc.identityservice.entity;

import com.fatechjsc.identityservice.utils.EnumActive;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    @Enumerated(EnumType.STRING)
    private EnumActive isActive;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "facebook_account_id")
    private Long facebook_account_id;

    @Column(name = "google_account_id")
    private Long google_account_id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name= "role_id")
    private Role role;

    @PrePersist
    private void onCreate(){
        //Thêm user tạo
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    private void onUpdate(){
        //Cập nhật user update
        updatedAt = LocalDateTime.now();
    }
    @PreRemove
    private void onRemove(){
        //Cập nhật user update
        updatedAt = LocalDateTime.now();
    }
}
