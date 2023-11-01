package com.fatechjsc.identityservice.configurations;

import com.fatechjsc.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userExist = repository.findByPhoneNumber(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with phone number : " + username)
        );
        var userDetails = new CustomUserDetails();
        userDetails.setPassword(userExist.getPassword());
        userDetails.setPhoneNumber(userExist.getPhoneNumber());
        userDetails.setRole(userExist.getRole().getName().name());
        return userDetails;
    }
}



