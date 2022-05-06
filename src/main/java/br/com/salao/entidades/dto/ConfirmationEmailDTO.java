package br.com.salao.entidades.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class ConfirmationEmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer code;
}
