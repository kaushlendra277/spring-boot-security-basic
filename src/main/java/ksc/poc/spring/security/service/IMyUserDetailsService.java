package ksc.poc.spring.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ksc.poc.spring.security.jpa.entities.User;

public interface IMyUserDetailsService extends UserDetailsService {

	User create(User user);
	
}
