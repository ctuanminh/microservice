package com.fatechjsc.identityservice.repository;

import com.fatechjsc.identityservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
