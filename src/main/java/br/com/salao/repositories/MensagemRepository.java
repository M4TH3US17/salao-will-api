package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
