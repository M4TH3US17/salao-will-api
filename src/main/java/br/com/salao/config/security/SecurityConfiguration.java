package br.com.salao.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests()
		
		.antMatchers(HttpMethod.POST, "/api/v1/clientes/cadastro").permitAll()
		
		.antMatchers(HttpMethod.GET, "/api/v1/agendamentos/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.POST, "/api/v1/agendamentos/cadastro").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.DELETE, "/api/v1/agendamentos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/agendamentos/**").hasAnyRole("ADMIN", "USER")
		
		.antMatchers(HttpMethod.GET, "/api/v1/clientes/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.DELETE, "/api/v1/clientes/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.PUT, "/api/v1/clientes/**").authenticated()
		
		.antMatchers("/api/v1/eventos/pagination").hasAnyRole("ADMIN", "USER")
		.antMatchers("/api/v1/eventos/cliente-aleatorio").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/eventos/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/v1/servicos/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.POST, "/api/v1/servicos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/v1/servicos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/servicos/**").hasRole("ADMIN")
		
		.and().httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.passwordEncoder(encoder())
		.withUser("user")
		.password(encoder().encode("user"))
		.roles("USER", "ADMIN");
	}
	
	@Bean(name = "encoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
