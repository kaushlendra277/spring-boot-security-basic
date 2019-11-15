package ksc.poc.spring.security.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ksc.poc.spring.security.jpa.entities.User;
import ksc.poc.spring.security.service.IMyUserDetailsService;

@RestController
@RequestMapping("admin")
public class UserResource {
	
	@Autowired
	private IMyUserDetailsService myUserDetailsService;
	
	@PostMapping("create-user")
	public ResponseEntity<User> create(
			@RequestBody User user
			){
		user.setPassword("admin");
		user.setActive(true);
		User createdUser = myUserDetailsService.create(user);
		return ResponseEntity.<User>status(HttpStatus.CREATED)
				.body(createdUser);
	}

}
