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

	/***
	 * 
	 * test1, test2 apis are for permisson based authorization
	 */
	
	// should be accessed by all authenticated user with the permission ACCESS_TEST1
	@GetMapping("/test1")
	public String test1() {
		return ("<h1> Test 1 </h1>");
	}

	// should be accessed by all authenticated user with the permission ACCESS_TEST2
	@GetMapping("/test2")
	public String test2() {
		return ("<h1> Test 2 </h1>");
	}
}
