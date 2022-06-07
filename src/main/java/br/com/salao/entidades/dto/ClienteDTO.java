package br.com.salao.entidades.dto;

import java.io.Serializable;

import br.com.salao.config.email.EmailCliente;
import br.com.salao.entidades.Cliente;
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
