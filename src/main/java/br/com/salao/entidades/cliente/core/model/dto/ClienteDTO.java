package br.com.salao.entidades.cliente.core.model.dto;

import java.io.Serializable;

import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.email.core.model.EmailCliente;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private EmailCliente emailCliente;
	private String contato;
	private Byte[] foto;

	public ClienteDTO(Cliente cliente) {
		login = cliente.getLogin();
		emailCliente = cliente.getEmailCliente();
		foto = cliente.getFoto();
		contato = cliente.getContato();
	}
}
