package com.pmrendszer.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.pmrendszer.security.jwt.AuthEntryPointJwt;
import com.pmrendszer.security.jwt.AuthTokenFilter;
import com.pmrendszer.security.services.UserDetailsServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		httpSec.cors().and()
//			.headers().frameOptions().disable().and()
			.addFilterBefore(corsFilterBean(), SessionManagementFilter.class)
			.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/swagger-ui.html", "/favicon.ico", "/swagger-resources/**", "/webjars/springfox-swagger-ui/**", "/v2/api-docs", "/csrf").permitAll()
					.antMatchers("/api/auth/**").permitAll()
//					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/api/project_manager/**").hasRole("PROJECT_MANAGER")
					.antMatchers("/api/team_leader/**").hasRole("TEAM_LEADER")
					.antMatchers("/api/developer/**").hasRole("DEVELOPER")
					.anyRequest().authenticated()
//			.and()
//				.httpBasic()
			.and()
				.logout().permitAll()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			.and()
				.csrf().disable()
				.exceptionHandling().authenticationEntryPoint(new AuthEntryPointJwt())
			.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public CorsFilter corsFilterBean() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("https://pmrendszer-react.herokuapp.com", "http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("XMLHttpRequest", "X-Requested-With", "Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", 
        		"Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
        config.setAllowedMethods(Arrays.asList("DELETE", "OPTIONS", "GET", "POST", "PATCH", "PUT"));
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
}