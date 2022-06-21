package br.com.salao.entidades.cliente.core.model.dto;

import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.cliente.core.model.Role;
import br.com.salao.entidades.email.core.model.EmailCliente;
import br.com.salao.entidades.email.core.model.dto.EmailClienteDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ModifyClienteDTO {

    private String login;
    private EmailClienteDTO emailCliente;
    private String contato;
    private Byte[] foto;
    private String senha;

    public ModifyClienteDTO(Cliente cliente) {
        login   = cliente.getLogin();
        foto    = cliente.getFoto();
        contato = cliente.getContato();
        senha   = cliente.getSenha();
        emailCliente.setEmail(cliente.getEmailCliente().getEmail());
    }
}
