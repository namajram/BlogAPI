package com.api.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.model.User;
import com.api.repository.UserDetailsRepository;

@Service
public class UserService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public User register(User user) {

		user.setPassword(passwordEncoder().encode(user.getPassword()));

		return userDetailsRepository.save(user);
	}

	public List<User> allDetails() {
		List<User> findAll = userDetailsRepository.findAll();
		return findAll;
	}

	public void delete(String username) {
		User user = userDetailsRepository.findByUsername(username);
		long userid = user.getUserId();
		userDetailsRepository.deleteById(userid);
	}
}
