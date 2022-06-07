package br.com.salao.config.email;

import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
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
