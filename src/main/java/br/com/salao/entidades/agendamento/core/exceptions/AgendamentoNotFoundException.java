package br.com.salao.entidades.agendamento.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgendamentoNotFoundException extends Exception {

    public AgendamentoNotFoundException(String msg){
        super(msg);
    }
}
