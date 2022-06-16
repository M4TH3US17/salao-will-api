package br.com.salao.entidades.servico.core.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "servico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	@Lob
	private Byte[] imagem;
	private Double preco;
	private String descricao;
	
	public Servico(Long id) {
		this.id = id;
	}
	
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

}
