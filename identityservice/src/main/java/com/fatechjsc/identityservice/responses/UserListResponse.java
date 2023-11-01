package com.fatechjsc.identityservice.responses;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {
    private int totalPages;
    private int total;
    private int pageIndex;
    private List<UserResponse> users;
}
