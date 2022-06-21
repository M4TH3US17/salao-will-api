package br.com.salao.entidades.email.core.model.dto;

import br.com.salao.entidades.email.core.model.EmailCliente;
import lombok.*;
@NoArgsConstructor @AllArgsConstructor
@Data
public class EmailClienteDTO {

    private String email;

    public EmailClienteDTO(EmailCliente emailCliente) {
        email = emailCliente.getEmail();
    }
}
