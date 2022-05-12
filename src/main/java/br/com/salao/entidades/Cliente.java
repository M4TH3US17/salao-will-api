package br.com.salao.entidades;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.*;

import br.com.salao.email.EmailCliente;
import lombok.*;

@Entity
@Table(name = "cliente")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Cliente implements Serializable, UserDetails {
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
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "cliente_roles",
			joinColumns = @JoinColumn(
					name = "cliente_login", referencedColumnName = "login", 
					table = "cliente", unique = false, 
					foreignKey = @ForeignKey(name = "cliente_fk", value = ConstraintMode.CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(
					name = "roles_id", referencedColumnName = "id", 
					table = "roles", unique = false,
					foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
	private Set<Role> roles = new HashSet<>();
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}
	@JsonIgnore
	@Override
	public String getUsername() {
		return this.login;
	}
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(login);
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
		return Objects.equals(login, other.login);
	}
}
