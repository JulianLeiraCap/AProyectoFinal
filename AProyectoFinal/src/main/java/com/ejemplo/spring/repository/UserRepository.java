package com.ejemplo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
