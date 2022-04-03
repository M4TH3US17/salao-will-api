package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
