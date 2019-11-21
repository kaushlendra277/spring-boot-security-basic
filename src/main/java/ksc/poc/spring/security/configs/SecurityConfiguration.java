package ksc.poc.spring.security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final String ACCESS_TEST1_AUTHORITY = "ACCESS_TEST1";
	private final String ACCESS_TEST2_AUTHORITY = "ACCESS_TEST2";

	// AUTHENTICATION
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// uncomment this if you want the only default user from application.properties file
		// super.configure(auth);
		auth
			.inMemoryAuthentication()
			.withUser("user").password("admin").authorities(ACCESS_TEST1_AUTHORITY).roles("USER") // even on adding ACCESS_TEST1_AUTHORITY spring is not able to call "/user" api ?
			.and()
			.withUser("test1").password("admin").roles("USER").authorities(ACCESS_TEST1_AUTHORITY) // even on adding .role spring is not able to call "/user" api ?
			.and()
			.withUser("test2").password("admin").roles("USER").authorities(ACCESS_TEST1_AUTHORITY, ACCESS_TEST2_AUTHORITY) // even on adding .role spring is not able to call "/user" api ?
			.and()
			.withUser(User
						.withUsername("admin")
						.password("admin")
						.authorities(ACCESS_TEST1_AUTHORITY,ACCESS_TEST2_AUTHORITY)
						.roles("ADMIN"))
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
		.antMatchers("/test1").hasAuthority(ACCESS_TEST1_AUTHORITY) // only user with this authority can access this endpoint
		.antMatchers("/test2").hasAuthority(ACCESS_TEST2_AUTHORITY)// only user with this authority can access this endpoint
		.antMatchers("/").permitAll() // all path in the current level of any role
		// .antMatchers("/**") // all path in the current level and sub path
		// .hasAnyRole("ADMIN")
		.and().formLogin()
		// .httpBasic() -- it gives basic authentication
		// .formLogin() - it gives session kind of funtionality[PREFERRED since it provide logout] // ?
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// NoOpPasswordEncoder takes userid and password as String
		return NoOpPasswordEncoder.getInstance();
	}

}
