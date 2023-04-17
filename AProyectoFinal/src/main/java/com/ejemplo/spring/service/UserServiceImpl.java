package com.ejemplo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.spring.model.User;
import com.ejemplo.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repo;
	
	@Override
	public List<User> findAll() {
		
		return repo.findAll();
	}

	@Override
	public User save(User user) {
		return repo.save(user);
	}

	@Override
	public Optional<User> deleteById(int id) {
		Optional<User> user= repo.findById(id);
		repo.deleteById(id);
		return user;
	}

	@Override
	public Optional<User> findById(int id) {
		return repo.findById(id);
	}
	
}
