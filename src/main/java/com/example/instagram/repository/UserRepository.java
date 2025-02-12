package com.example.instagram.repository;

import com.example.instagram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
}
