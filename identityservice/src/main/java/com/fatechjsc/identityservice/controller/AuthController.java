package com.fatechjsc.identityservice.controller;

import com.fatechjsc.identityservice.dto.UserLoginDto;
import com.fatechjsc.identityservice.dto.UserUpdateDto;
import com.fatechjsc.identityservice.dto.UserDto;
import com.fatechjsc.identityservice.responses.UserListResponse;
import com.fatechjsc.identityservice.responses.UserResponse;
import com.fatechjsc.identityservice.service.UserServiceImpl;
import com.fatechjsc.identityservice.utils.EnumActive;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) throws Exception {
        var user = userService.add(userDto);
        var response = new UserResponse();
        response.setUserDto(userDto);
        response.setMessage("Register user success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDto loginDto, BindingResult result) throws Exception{
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors.toString());
        }
        var token = userService.login(loginDto.getPhoneNumber(), loginDto.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @Valid @RequestBody UserUpdateDto model) throws Exception{
        userService.update(id, model);
        return ResponseEntity.ok("update user success");
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id) throws Exception{
        userService.delete(id);
        return ResponseEntity.ok("delete user success");
    }

    @GetMapping("find")
    public ResponseEntity<UserListResponse> find(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(name = "status") EnumActive status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) throws Exception{
        // tao Pageable tu thong tin page & limit
        PageRequest pageRequest = PageRequest.of(page-1, limit, Sort.by("id").ascending());
        Page<UserResponse> userPage = userService.findAll(keyword, status, pageRequest);
        var totalPages = userPage.getTotalPages();
        var total = userPage.getSize();
        List<UserResponse> users = userPage.getContent();
        return ResponseEntity.ok(UserListResponse
                .builder()
                .users(users)
                .pageIndex(page)
                .total(total)
                .totalPages(totalPages)
                .build());
    }
}
