package br.com.salao.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.salao.email.EmailCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	private String login;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	private String contato;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="email_cliente")
	private EmailCliente emailCliente;
	@Lob
	@Column(nullable = true)
	private Byte[] foto;
	
	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
}
