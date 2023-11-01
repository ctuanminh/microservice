package com.fatechjsc.identityservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatechjsc.identityservice.utils.EnumActive;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("password")
    @NotNull(message = "Password cannot empty")
    @Size(min = 8, message = "Password length must be 8 characters")
    private String password;

    @JsonProperty("is_active")
    private EnumActive isActive;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("facebook_account_id")
    private Long facebook_account_id;

    @JsonProperty("google_account_id")
    private Long google_account_id;

    @JsonProperty("role_id")
    private Long roleId;
}
