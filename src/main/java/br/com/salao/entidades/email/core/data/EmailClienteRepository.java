package br.com.salao.entidades.email.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.email.core.model.EmailCliente;

@Repository
public interface EmailClienteRepository extends JpaRepository<EmailCliente, String> {

}
