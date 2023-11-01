package com.fatechjsc.identityservice.repository;

import com.fatechjsc.identityservice.entity.User;
import com.fatechjsc.identityservice.utils.EnumActive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
        "u.isActive = :status " +
        "AND (:keyword IS NULL OR :keyword = '' OR u.fullName LIKE %:keyword% OR u.address LIKE %:keyword%)")
    Page<User> searchUsers(@Param("status") EnumActive status,
                           @Param("keyword") String keyword, Pageable pageable);

}
