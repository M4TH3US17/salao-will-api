package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
