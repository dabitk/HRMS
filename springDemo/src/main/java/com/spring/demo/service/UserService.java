package com.spring.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.domain.User;
import com.spring.demo.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public void deleteUser(long id) {
		Optional<User> userOpt = userRepository.findById(id);
		
		if(userOpt.isPresent()) {
			//User user = userOpt.get();		
			userRepository.deleteById(id);
		}
	}
	
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}
}
