package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findRoleByNome(String name);
}