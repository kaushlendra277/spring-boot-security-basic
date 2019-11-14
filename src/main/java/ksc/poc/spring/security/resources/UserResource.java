package ksc.poc.spring.security.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ksc.poc.spring.security.jpa.entities.User;
import ksc.poc.spring.security.service.IMyUserDetailsService;

@RestController
@RequestMapping("admin")
public class UserResource {
	
	@Autowired
	private IMyUserDetailsService myUserDetailsService;
	
	@GetMapping("create-user")
	public ResponseEntity<User> create(
			@RequestParam("name") String name,
			@RequestParam("role") String roles
			){
		User user = new User();
		user.setPassword("admin");
		user.setActive(true);
		user.setRoles(roles);
		user.setUserName(name);
		User createdUser = myUserDetailsService.create(user);
		return ResponseEntity.<User>status(HttpStatus.CREATED)
				.body(createdUser);
	}

}
