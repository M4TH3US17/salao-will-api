package br.com.salao.entidades.agendamento.core.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.salao.entidades.agendamento.core.model.Agendamento;
import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.cliente.core.model.dto.ClienteDTO;
import br.com.salao.entidades.servico.core.model.Servico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
