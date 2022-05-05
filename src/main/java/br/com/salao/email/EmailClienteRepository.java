package br.com.salao.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailClienteRepository extends JpaRepository<EmailCliente, String> {

}
