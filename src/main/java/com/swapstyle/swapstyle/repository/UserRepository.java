package com.swapstyle.swapstyle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstyle.swapstyle.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}