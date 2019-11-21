package ksc.poc.spring.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// uncomment this if you want the only default user from application.properties file
		// super.configure(auth);
		auth
			.inMemoryAuthentication()
			.withUser("ksc2").password(passwordEncoder().encode("admin2")).roles("USER")
			.and()
			.withUser("ksc3").password(passwordEncoder().encode("admin3")).roles("USER")
			;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// NoOpPasswordEncoder encodes string password
		//return NoOpPasswordEncoder.getInstance();
		// BCryptPasswordEncoder password as String
		return new BCryptPasswordEncoder();
	}

}
