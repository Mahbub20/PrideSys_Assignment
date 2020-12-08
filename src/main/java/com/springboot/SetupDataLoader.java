package com.springboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.models.Role;
import com.springboot.models.User;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
            return;
		
		 createRoleIfNotFound("ROLE_ADMIN");
		 
		 Role adminRole = roleRepo.findByName("ROLE_ADMIN");
	        User user = new User();
	        user.setFirstName("Administrator");
	        user.setLastName("Role");
	        user.setEmail("admin@gmail.com");
	        user.setPassword(passwordEncoder.encode("admin"));
	        user.setMobile("01816121976");
	        user.setAddress("Uttara, Dhaka");
	        user.setRoles(Arrays.asList(adminRole));
	        userRepo.save(user);

	       alreadySetup = true;
		
	}

	@Transactional
    Role createRoleIfNotFound(
      String name) {
 
        Role role = roleRepo.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepo.save(role);
        }
        return role;
    }

}
