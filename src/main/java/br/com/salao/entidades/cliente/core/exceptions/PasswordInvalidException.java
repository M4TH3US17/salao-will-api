package br.com.salao.entidades.cliente.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordInvalidException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordInvalidException(String msg) {
		super(msg);
	}
}
