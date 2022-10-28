package com.pushyourself11.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.pushyourself11.Entity.Users;
import com.pushyourself11.dao.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Optional<Users> result = userRepository.findByEmail(email);
			
		Users dbUser = null;

		if (result.isPresent()) {
			dbUser = result.get();
			return dbUser;
		}
		else {
			throw new UsernameNotFoundException("Bad credentials");
		}

	}
}
