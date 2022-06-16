package br.com.salao.entidades.agendamento.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import br.com.salao.entidades.servico.core.model.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.salao.entidades.cliente.core.model.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Entity
@Table(name = "agendamento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor @Getter @Setter @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
	private LocalDateTime data;
	
	@OneToOne
	private Cliente cliente;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "agendamento_servico",
			joinColumns = @JoinColumn(name = "agendamento_id"),
			inverseJoinColumns = @JoinColumn(name = "servico_id"))
	private List<Servico> servicos;
	
	public Double getTotal() {
		double sum = 0.0;
		for(Servico x: servicos) {
			sum += x.getPreco();
		}
		return sum;
	}
}