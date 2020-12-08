package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
