package com.ejemplo.spring.service;

import java.util.List;
import java.util.Optional;

import com.ejemplo.spring.model.User;

public interface UserService {
	
	public List<User> findAll();
	public User save(User user);
	public Optional<User> deleteById(int id);
	public Optional<User> findById(int id);
	
}
