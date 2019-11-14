package ksc.poc.spring.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ksc.poc.spring.security.dtos.MyUserDetails;
import ksc.poc.spring.security.jpa.entities.User;
import ksc.poc.spring.security.jpa.repos.UserRepository;
import ksc.poc.spring.security.service.IMyUserDetailsService;

@Service
public class MyUserDetailsService implements IMyUserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> oUser = userRepository.findByUserName(username);
		oUser.orElseThrow(() ->new UsernameNotFoundException("INNVALID USER"));
		return oUser.map(MyUserDetails:: new).get();
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

}
