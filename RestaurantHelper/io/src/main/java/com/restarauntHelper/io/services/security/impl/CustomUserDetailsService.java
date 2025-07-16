package com.restarauntHelper.io.services.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.restarauntHelper.io.model.entities.User;
import com.restarauntHelper.io.repositories.UserRepositories;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositories userRepository; 
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
								.orElseThrow(() -> new RuntimeException("Usuário não encontrado tente novamente com outro usernma,e usernmae usado:" + username));
		
		return org.springframework.security.core.userdetails.User
															.builder()
															.username(username)
															.password(user.getPassword())
															.authorities(user.getAuthorities())
															.build();
		
		}

	
	
}
