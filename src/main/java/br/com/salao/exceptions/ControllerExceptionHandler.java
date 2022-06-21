package br.com.salao.exceptions;

import br.com.salao.entidades.agendamento.core.exceptions.AgendamentoNotFoundException;
import br.com.salao.entidades.cliente.core.exceptions.ClienteNotFoundException;
import br.com.salao.entidades.evento.core.exceptions.EventoNotFoundException;
import br.com.salao.entidades.servico.core.exceptions.ServicoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler({
            ClienteNotFoundException.class, AgendamentoNotFoundException.class,
            ServicoNotFoundException.class, EventoNotFoundException.class,
            NoSuchElementException.class})
    public ResponseEntity<ErrorDetails> entidadeNotFoundException(Exception e){
        ErrorDetails error = new ErrorDetails(LocalDateTime.now().format(formatter), HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}
