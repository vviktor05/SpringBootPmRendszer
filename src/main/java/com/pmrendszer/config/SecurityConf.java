package com.pmrendszer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.pmrendszer.service.UserDetailsServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("d").password(passwordEncoder().encode("d")).roles("DEVELOPER");
//		auth.inMemoryAuthentication().withUser("tm").password(passwordEncoder().encode("tm")).roles("PROJECT_MANAGER");
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		httpSec.authorizeRequests()
				.antMatchers("/api/**").authenticated()
//				.antMatchers("/api/developer/**").hasRole("DEVELOPER")
//				.antMatchers("/api/team_leader/**").hasRole("TEAM_LEADER")
//				.antMatchers("/api/project_manager/**").hasRole("PROJECT_MANAGER") / access("hasRole('ROLE_PROJECT_MANAGER')"), 
//																						hasAnyAuthority("ROLE_PROJECT_MANAGER") / Doesn't work
			.and()
				.httpBasic()
			.and()
				.logout().permitAll()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			.and()
				.csrf().disable();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServiceImpl);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}