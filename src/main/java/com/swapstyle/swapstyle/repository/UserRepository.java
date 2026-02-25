package com.swapstyle.swapstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstyle.swapstyle.entity.User;

public interface UserRepository extends JpaRepository <User, Integer>  {

}