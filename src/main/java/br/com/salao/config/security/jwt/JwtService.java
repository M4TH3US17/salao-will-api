package br.com.salao.config.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Cliente;
import io.jsonwebtoken.*;

@Service
public class JwtService {

	@Value("jwt.secret-key")
	private String secret;
	private final long expiration = 86400000;

	public String generationToken(Cliente obj) {
		return Jwts.builder()
				.setSubject(obj.getLogin())
				.claim("roles", obj.getRoles())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public Claims captureToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean isValidToken(String token) {
		try {
			Claims claims = captureToken(token);
			return claims.getExpiration().after(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String captureCliente(String token) throws ExpiredJwtException {
		return (String) captureToken(token).getSubject();
	}
}
