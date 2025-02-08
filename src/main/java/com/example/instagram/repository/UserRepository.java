package com.example.instagram.repository;

import com.example.instagram.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Long countByEmail(String email);
}
