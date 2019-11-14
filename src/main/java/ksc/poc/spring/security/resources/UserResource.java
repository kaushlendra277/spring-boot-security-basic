package ksc.poc.spring.security.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksc.poc.spring.security.jpa.entities.User;

@RestController
@RequestMapping("admin")
public class UserResource {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public ResponseEntity<User> create(@RequestBody User user){
		//User createdUser = userDetailsService.create(user);
		return null;
	}

}
