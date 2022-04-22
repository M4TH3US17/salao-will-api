package br.com.salao.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
@Entity
@Table(name = "evento")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Lob
	private Byte[] imagem;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private LocalDateTime data;
	private String descricao;
	
	@ManyToMany
	@JoinTable(
			name = "evento_cliente",
			joinColumns = @JoinColumn(name = "evento_id"),
			inverseJoinColumns = @JoinColumn(name = "login")
			)
	private List<Cliente> participantes;

	@ManyToOne
	private Cliente ganhador;
	@OneToOne
	private Servico premio;
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(id, other.id);
	}
}
