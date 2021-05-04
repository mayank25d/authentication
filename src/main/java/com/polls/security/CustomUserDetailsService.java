package com.polls.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polls.model.User;
import com.polls.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// let people login with either username or email
		User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
				orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: "+usernameOrEmail)
		);
		
		return UserPrincipal.create(user);
	}
	
	// this method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepo.findById(id).orElseThrow(() ->
				new UsernameNotFoundException("User not found with id: "+id));
		
		return UserPrincipal.create(user);
	}

}
