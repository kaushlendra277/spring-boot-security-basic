package ksc.poc.spring.security.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	// Whitelist/public API
	// should be accessed by all un-authenticated user
	@GetMapping
	public String home() {
		return ("<h1> Hello Principal </h1>");
	}

	// should be accessed by all authenticated user with the role ADMIN or USER
	@GetMapping("/user")
	public String user() {
		return ("<h1> Hello User </h1>");
	}

	// should be accessed by all authenticated user with the role ADMIN
	@GetMapping("/admin")
	public String admin() {
		return ("<h1> Hello Admin </h1>");
	}
}
