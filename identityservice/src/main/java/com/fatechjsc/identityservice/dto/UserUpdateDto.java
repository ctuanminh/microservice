package com.fatechjsc.identityservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @JsonProperty("full_name")
    @NotNull(message = "full name length must 2 characters")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("is_update_password")
    private Boolean isUpdatePassword;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;
}
