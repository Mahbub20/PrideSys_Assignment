package com.springboot.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springboot.dto.UserRegistrationDto;
import com.springboot.models.User;

public interface UserService extends UserDetailsService {
	
	User save(UserRegistrationDto userRegistrationDto);

}
