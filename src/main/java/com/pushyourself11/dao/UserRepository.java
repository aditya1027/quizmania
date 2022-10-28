package com.pushyourself11.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.Entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {

	
	 Optional<Users> findByEmail(String email);
}
