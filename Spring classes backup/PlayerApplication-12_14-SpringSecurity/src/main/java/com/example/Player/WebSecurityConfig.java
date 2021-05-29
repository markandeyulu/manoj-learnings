package com.example.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/* Steps for Spring Security : 
1. Security Configuration class
2. Protect given endpoints
3. Basic or form based authentication
4. Credentials for the users and roles ( with the help of ImMemory Authentication here )
5. Encrypt your password
6. Getting user information via principal
7. AccessDeniedException*/

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//org.springframework.security - abstract class

	
	/* Do the below steps here,
	 *  2. Protect given endpoints
		3. Basic or form based authentication
		7. AccessDeniedException

	 */
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler; // this is an interface need to be implemented. See CustomAccessDeniedException.java
	
/*	@Autowired
	PasswordEncoder passwordEncoder;//This is for encryption ( when we dont give {noop}). Its not working in the demo
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/welcome").permitAll()
		//.antMatchers("/userinfo").permitAll()//commented to give userinfor to USER and ADMIN access to check the Principal to get the user related details to track user
		.antMatchers("/userinfo").hasAnyRole("USER", "ADMIN")// for multiple roles
		.antMatchers("/allplayers", "/playerbyid/{playerId}", "/playerbyname/{playerName}")/*why .hasAnyAuthority("USER", "ADMIN") not wokring ? - hasAnyRole is there*/.hasRole("USER")
		.antMatchers("/savePlayer", "/updateplayer/{playerId}/{playerAverageScore}", "/removeplayer/{playerId}").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.csrf()//cross site request forgery need to be disabled in oreder to avoid hacker site redirection
		.disable()
		//.httpBasic()//enable httpbasic // pop up window for username pwd ( in techm sites). // Basic or form based authentication - BASIC - step 3. you will get pop up unless you give right username pwd to access the site. if you dont have role specific to the URL you will get error as "Http Status 403:Access is deniedNOT AUTHERISED" which we handled in the AccessDenied Handler
		.formLogin()// we have a challenege in formbased if we use rest client // we can give default login page also
		.and()// why and ? - to get http. methods here to and
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
		
		// This is sequential. All these are methods which returns for next methods
		
	
	}

	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
			//similar to in memory auth , we have JDBC auth and OAuth .
		auth.inMemoryAuthentication()
		.withUser("john").password("{noop}john") // {noop} - no password encryption needed
		.roles("USER")
		.and()
		.withUser("peter").password("{noop}peter")
		.roles("USER")
		.and()
		.withUser("sundar").password("{noop}sundar")
		.roles("ADMIN")
		.and()
		.withUser("scott").password("{noop}scott")
		.roles("ADMIN");
		
			
/*			
			auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
			.withUser("john").password("john") //Since we removed {noop} , we get error while accessing service as "java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"". so use PasswordEncoder
			.roles("USER")
			.and()
			.withUser("peter").password("peter")
			.roles("USER")
			.and()
			.withUser("sundar").password("sundar")
			.roles("ADMIN")
			.and()
			.withUser("scott").password("scott")
			.roles("ADMIN");*/
			
		}

}
