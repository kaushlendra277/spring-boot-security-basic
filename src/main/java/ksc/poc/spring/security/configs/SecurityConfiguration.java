package ksc.poc.spring.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// AUTHENTICATION
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// uncomment this if you want the only default user from application.properties file
		// super.configure(auth);
		auth
			.inMemoryAuthentication()
			.withUser("ksc2").password("admin2").roles("USER")
			.and()
			.withUser("ksc3").password("admin3").roles("ADMIN")
			;
		
	}
	
	
	// AUTHORIZATION
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN") // admin apis- all path in the current level for ADMIN role user
		.antMatchers("/user").hasAnyRole("USER","ADMIN") // user apis- all path in the current level for USER and ADMIN role user
		.antMatchers("/").permitAll() // all path in the current level of any role
		// .antMatchers("/**") // all path in the current level and sub path
		// .hasAnyRole("ADMIN")
		.and().formLogin(); // ?
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		// NoOpPasswordEncoder takes userid and password as String
		return NoOpPasswordEncoder.getInstance();
	}

}
