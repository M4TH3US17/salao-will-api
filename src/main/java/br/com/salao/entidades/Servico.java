package br.com.salao.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import br.com.salao.entidades.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servico")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
		Servico other = (Servico) obj;
		return Objects.equals(id, other.id);
	}
}
