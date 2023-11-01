package com.fatechjsc.identityservice.service;

import com.fatechjsc.identityservice.configurations.JwtTokenUtil;
import com.fatechjsc.identityservice.dto.UserUpdateDto;
import com.fatechjsc.identityservice.dto.UserDto;
import com.fatechjsc.identityservice.entity.User;
import com.fatechjsc.identityservice.exceptions.NotFoundException;
import com.fatechjsc.identityservice.exceptions.PermissionDenyException;
import com.fatechjsc.identityservice.repository.UserRepository;
import com.fatechjsc.identityservice.responses.UserResponse;
import com.fatechjsc.identityservice.utils.EnumActive;
import com.fatechjsc.identityservice.utils.EnumRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public User find(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found by id : " + id ));
    }

    @Override
    public Page<UserResponse> findAll(String keyword, EnumActive status, PageRequest pageRequest) throws Exception {
        var usersPage = repository.searchUsers(status, keyword, pageRequest);
        return usersPage.map(UserResponse::fromUser);
    }

    @Override
    public User add(UserDto userDto) throws Exception {
        var role = roleService.find(userDto.getRoleId());
        if(role.getName().name().toUpperCase().equals(EnumRole.ADMIN.name())){
            throw new PermissionDenyException("You cannot register account admin");
        }
        var user = modelMapper.map(userDto, User.class);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return repository.save(user);
    }

    @Override
    public String login(String phoneNumber, String password) throws Exception {
        Optional<User> optionalUser = repository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("Tài khoản hoặc mật khẩu không chính xác");
        }
        var user = optionalUser.get();
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new NotFoundException("Tài khoản hoặc mật khẩu không chính xác");
        }
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber, password
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public User update(Long id, UserUpdateDto updateDto) throws Exception {
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with Id = " + id));
        modelMapper.map(updateDto, user);
        if(updateDto.getIsUpdatePassword()){
            user.setPassword(passwordEncoder.encode(updateDto.getNewPassword()));
        }
        return repository.save(user);
    }

    @Override
    public void delete(Long id) throws Exception{
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id = " + id));
        user.setIsActive(EnumActive.REMOVE);
        repository.save(user);
    }
}
