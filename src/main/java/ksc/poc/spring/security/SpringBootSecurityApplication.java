package ksc.poc.spring.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * 
 * @author chauhan_k
 * @source youtube
 * @tutor javabrains
 * @channel Spring Security Basics
 *
 */
@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class) // ?
public class SpringBootSecurityApplication implements CommandLineRunner { 

	//@Autowired
	//private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*
		User user = new User();
		user.setActive(true);
		user.setPassword("admindb");
		user.setRoles("ROLE_ADMIN");
		user.setUserName("admin");
		
		userRepository.save(user);
		*/
	}

}
