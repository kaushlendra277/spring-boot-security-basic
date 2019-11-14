package ksc.poc.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;

import ksc.poc.spring.security.jpa.entities.User;
import ksc.poc.spring.security.jpa.repos.UserRepository;

/***
 * 
 * @author chauhan_k
 * @source youtube
 * @tutor javabrains
 * @channel Spring Security Basics
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class) // ?
public class SpringBootSecurityApplication implements CommandLineRunner { 

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setActive(true);
		user.setPassword("admindb");
		user.setRoles("ROLE_ADMIN");
		user.setUserName("user");
		
		userRepository.save(user);
	}

}
