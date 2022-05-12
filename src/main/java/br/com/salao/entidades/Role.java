package br.com.salao.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor @AllArgsConstructor
public class Role implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@JsonIgnore
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public String getRole() {
		return nome;
	}
	public void setRole(String role) {
		this.nome = role;
	}
	@Override
	public String getAuthority() {
		return this.nome;
	}
	
	
}
