package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
