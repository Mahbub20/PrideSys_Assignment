package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import com.springboot.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserDetailsService {
	
	User findByEmail(String email);

}
