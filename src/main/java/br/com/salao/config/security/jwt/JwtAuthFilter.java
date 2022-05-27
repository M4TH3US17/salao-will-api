package br.com.salao.config.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.salao.config.security.impl.ImplementationUserDetailsService;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private ImplementationUserDetailsService userDetailsImpl;
	
	public JwtAuthFilter(JwtService jwtService, ImplementationUserDetailsService userDetailsImpl) {
		this.jwtService = jwtService;
		this.userDetailsImpl = userDetailsImpl;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.substring(7);
			boolean isValidToken = jwtService.isValidToken(token);
			
			if(isValidToken == true) {
				String loginCliente = jwtService.captureCliente(token);
				UserDetails login = userDetailsImpl.loadUserByUsername(loginCliente);
				
				UsernamePasswordAuthenticationToken obj = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
				obj.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(obj);
			}
		}
		filterChain.doFilter(request, response);
	}

}
