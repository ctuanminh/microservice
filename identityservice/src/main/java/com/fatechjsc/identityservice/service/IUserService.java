package com.fatechjsc.identityservice.service;

import com.fatechjsc.identityservice.dto.UserUpdateDto;
import com.fatechjsc.identityservice.dto.UserDto;
import com.fatechjsc.identityservice.entity.User;
import com.fatechjsc.identityservice.responses.UserResponse;
import com.fatechjsc.identityservice.utils.EnumActive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IUserService {
    User find(Long id);

    Page<UserResponse> findAll(String keyword, EnumActive status, PageRequest pageRequest) throws Exception;
    User add(UserDto userDto) throws Exception;

    String login(String phoneNumber, String password) throws Exception;

    User update(Long id, UserUpdateDto updateDto) throws Exception;

    void delete(Long id) throws Exception;
}
