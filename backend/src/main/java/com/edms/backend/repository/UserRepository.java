package com.edms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edms.backend.entity.Role;
import com.edms.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

}
