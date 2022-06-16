package br.com.salao.entidades.evento.core.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.salao.entidades.cliente.core.model.dto.ClienteDTO;
import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.evento.core.model.Evento;
import br.com.salao.entidades.servico.core.model.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EventoDTO implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long id;
		private String nome;
		private Byte[] imagem;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
		private LocalDateTime data;
		private String descricao;
		
		private List<ClienteDTO> participantes;

		private ClienteDTO ganhador;
	    private Servico premio;
	    
		public EventoDTO(Evento evento, Cliente ganhador, List<Cliente> participantes) {
			if(evento.getGanhador() != null) {
				this.ganhador = new ClienteDTO(evento.getGanhador());
				this.participantes = evento.getParticipantes().stream().map(x -> new ClienteDTO(x))
						.collect(Collectors.toList());
			}
			id = evento.getId();
			nome = evento.getNome();
			imagem = evento.getImagem();
			data = evento.getData();
			descricao = evento.getDescricao();
			premio = evento.getPremio();
		}
	
	    
}
