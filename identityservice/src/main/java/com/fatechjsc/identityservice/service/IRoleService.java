package com.fatechjsc.identityservice.service;

import com.fatechjsc.identityservice.entity.Role;

import java.util.List;

public interface IRoleService {
    Role find(Long id) throws Exception;
    List<Role> findAll();
}
