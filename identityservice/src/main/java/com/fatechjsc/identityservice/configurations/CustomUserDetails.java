package com.fatechjsc.identityservice.configurations;

import com.fatechjsc.identityservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private String phoneNumber;
    private String password;
    private String role;

    //Gán quyền (role) cho user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorityList;
    }

    //Return password
    @Override
    public String getPassword() {
        return password;
    }

    //Return username ở đây là phoneNumber
    @Override
    public String getUsername() {
        return phoneNumber;
    }

    //setAccount không hết hạn
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //set account không bị khoá
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
