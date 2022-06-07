package br.com.salao.entidades.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.salao.entidades.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AgendamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime data;
	private Double total;
	
	private ClienteDTO cliente;
	
	private List<Servico> servicos;
	
	public AgendamentoDTO(Agendamento agendamento, Cliente cliente) {
		id = agendamento.getId();
		data = agendamento.getData();
		total = agendamento.getTotal();
		servicos = agendamento.getServicos();
		this.cliente = new ClienteDTO(cliente);
	}
}
