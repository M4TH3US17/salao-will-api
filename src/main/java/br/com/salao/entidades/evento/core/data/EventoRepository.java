package br.com.salao.entidades.evento.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.evento.core.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
