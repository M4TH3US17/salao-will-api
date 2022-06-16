package br.com.salao.entidades.email.core.model;

import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
@Entity
@Table(name="email_clientes")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmailCliente {

	@Id
	@Column(unique = true)
	@EqualsAndHashCode.Include
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "confirmation_code")
	private Integer confirmationCode;
	private Boolean confirmed;

}
