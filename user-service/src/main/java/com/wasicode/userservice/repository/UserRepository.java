package com.wasicode.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wasicode.userservice.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
