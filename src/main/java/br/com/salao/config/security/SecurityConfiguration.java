package br.com.salao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.salao.config.security.impl.ImplementationUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementationUserDetailsService userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().authorizeRequests()
		
		.antMatchers(HttpMethod.POST, "/api/v1/clientes/cadastro").permitAll()
		
		.antMatchers(HttpMethod.GET, "/api/v1/agendamentos/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/api/v1/agendamentos/cadastro").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/api/v1/agendamentos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/agendamentos/**").hasRole("USER")
		
		.antMatchers(HttpMethod.GET, "/api/v1/clientes/**").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/api/v1/clientes/**").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/api/v1/clientes/**").authenticated()
		
		.antMatchers("/api/v1/eventos/pagination").hasRole("USER")
		.antMatchers("/api/v1/eventos/cliente-aleatorio").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/eventos/**").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/api/v1/servicos/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/api/v1/servicos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/v1/servicos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/v1/servicos/**").hasRole("ADMIN")
		
		.and().httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/h2-console/**");
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
