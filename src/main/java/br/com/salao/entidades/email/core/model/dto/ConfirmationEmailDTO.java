package br.com.salao.entidades.email.core.model.dto;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class ConfirmationEmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer code;
}
