package br.com.salao.entidades.servico.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicoNotFoundException extends Exception {

    public ServicoNotFoundException(String msg) {
        super(msg);
    }
}
