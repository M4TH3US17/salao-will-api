package br.com.salao.config.security.jwt.dto;

import java.io.Serializable;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
}
