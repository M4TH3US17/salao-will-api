package br.com.salao.entidades.cliente.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends Exception {

    public ClienteNotFoundException(String msg) {
        super(msg);
    }
}
