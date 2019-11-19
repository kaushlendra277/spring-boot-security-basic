package ksc.poc.spring.security.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ksc.poc.spring.security.jwt.models.AuthenticationRequest;
import ksc.poc.spring.security.jwt.models.AuthenticationResponse;
import ksc.poc.spring.security.jwt.utils.JwtUtil;
import ksc.poc.spring.security.service.IMyUserDetailsService;
import ksc.poc.spring.security.service.impl.MyUserDetailsService;

@RestController
public class HomeResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IMyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
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
	
	@PostMapping("authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
					);
		}catch (AuthenticationException e) {
			throw e;
		}
		final UserDetails principal = myUserDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(principal);
		return ResponseEntity.<AuthenticationResponse>ok(new AuthenticationResponse(jwt));
	}
}
