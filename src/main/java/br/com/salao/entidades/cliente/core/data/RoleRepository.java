package br.com.salao.entidades.cliente.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.cliente.core.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findRoleByNome(String name);

}
