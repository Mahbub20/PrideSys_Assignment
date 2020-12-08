package com.springboot.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.dto.UserRegistrationDto;
import com.springboot.models.Role;
import com.springboot.models.User;
import com.springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
		
		User user = new User(userRegistrationDto.getFirstName(),userRegistrationDto.getLastName(),
				userRegistrationDto.getEmail(), passwordEncoder.encode(userRegistrationDto.getPassword()),
				userRegistrationDto.getMobile(), userRegistrationDto.getAddress(),
				Arrays.asList(new Role("ROLE_USER")));
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Invalid email or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
