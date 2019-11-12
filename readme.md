# Spring Boot Security Authorization

## This POC includes in-memory authentication using default schema[BAD] and hard coded user in the code[BAD]

```java
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	/***
	 * 
	 * - For In memory database authentication it is required, but no external configuration required
	 */
	@Autowired
	private DataSource dataSource;
	
	// AUTHENTICATION
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// in case of any confusion refer notes
		auth
			.jdbcAuthentication() // jdbc authentication
			.dataSource(dataSource)
			.withDefaultSchema() // Spring sec default schema 
			.withUser(User.withUsername("user").password("admin").roles("USER")) // record 1 in default spring schema
			.withUser(User.withUsername("admin").password("admin").roles("ADMIN")) // record 2 in default spring schema
			;
	}
	
	
	/***
	 *  AUTHORIZATION
	 *  
	 *  Note :  Order of ant matcher is imp, 
	 *  it **MUST** be from least privileged role to highest privileged role 
	 */
	// 
	// 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
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

```

## POC endpoints
http://localhost:8080 - unauthenticated user<br />
http://localhost:8080/user - authenticated user with the role either ADMIN or USER <br />
http://localhost:8080/admin - authenticated user with the ADMIN role <br />
