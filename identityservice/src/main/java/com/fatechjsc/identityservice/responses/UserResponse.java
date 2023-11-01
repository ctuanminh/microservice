package com.fatechjsc.identityservice.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatechjsc.identityservice.dto.UserDto;
import com.fatechjsc.identityservice.entity.User;
import com.fatechjsc.identityservice.utils.EnumActive;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse extends BaseResponse {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("is_active")
    private EnumActive isActive;


    @JsonProperty("message")
    private String message;

    @JsonProperty("user")
    private UserDto userDto;

    public static UserResponse fromUser(User user){
        UserResponse userResponse = UserResponse.builder()
                .fullName(user.getFullName())
                .address(user.getAddress())
                .isActive(user.getIsActive())
                .build();
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}
