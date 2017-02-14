package org.did.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication().withUser("admin").password("1234")
		.roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("1234")
		.roles("USER");*/
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
		.authoritiesByUsernameQuery("select username as principal, roles as role from userrole where username=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new Md5PasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/Operations","/consulterCompte").hasRole("USER");//opé faite par l'utilisateur
		http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");//opération faites uniquement par l'admin
		http.exceptionHandling().accessDeniedPage("/403");//si l'utilisateur n'a pas le droit de faire des opérations, il est réorienté vers 403
	}
}
