package com.fatechjsc.identityservice.service;

import com.fatechjsc.identityservice.entity.Role;
import com.fatechjsc.identityservice.exceptions.NotFoundException;
import com.fatechjsc.identityservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository repository;
    @Override
    public Role find(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with id=" + id));
    }

    @Override
    public List<Role> findAll() {
        return null;
    }
}
