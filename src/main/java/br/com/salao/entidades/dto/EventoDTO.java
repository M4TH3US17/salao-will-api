package br.com.salao.entidades.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.salao.entidades.Cliente;
import br.com.salao.entidades.Evento;
import br.com.salao.entidades.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
				this.participantes = evento.getParticipantes().stream().map(x -> new ClienteDTO(x)).toList();
			}
			id = evento.getId();
			nome = evento.getNome();
			imagem = evento.getImagem();
			data = evento.getData();
			descricao = evento.getDescricao();
			premio = evento.getPremio();
		}
	
	    
}
