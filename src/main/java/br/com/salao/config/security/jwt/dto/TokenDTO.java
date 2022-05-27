package br.com.salao.config.security.jwt.dto;

import java.io.Serializable;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class TokenDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String token;
	
}
