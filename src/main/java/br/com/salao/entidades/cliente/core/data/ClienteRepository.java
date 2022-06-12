package br.com.salao.entidades.cliente.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.cliente.core.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
