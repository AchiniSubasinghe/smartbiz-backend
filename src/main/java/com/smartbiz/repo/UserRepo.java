package com.smartbiz.repo;

import com.smartbiz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String userName);
    List<User> findByUsernameStartingWithIgnoreCase(String username);
    boolean existsByUsername (String username);

    Optional<User> findByFullName(String fullName);
    Optional<User> findByEmail(String email);
}
