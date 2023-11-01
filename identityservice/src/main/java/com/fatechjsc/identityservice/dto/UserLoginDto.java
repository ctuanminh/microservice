package com.fatechjsc.identityservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "Phone number length must be 10 characters")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "Password not empty")
    @JsonProperty("password")
    private String password;
}
