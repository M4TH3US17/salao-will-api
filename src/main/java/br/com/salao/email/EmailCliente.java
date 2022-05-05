package br.com.salao.email;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="email_clientes")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class EmailCliente {

	@Id
	@Column(unique = true)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "confirmation_code")
	private Integer confirmationCode;
	private Boolean confirmed;
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailCliente other = (EmailCliente) obj;
		return Objects.equals(email, other.email);
	}
}
