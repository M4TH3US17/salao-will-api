package br.com.salao.repositories;

import java.time.LocalDate;import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	@Query("SELECT ag FROM Agendamento ag " + "JOIN ag.cliente " 
	        + "WHERE ag.data LIKE CONCAT(?1, '%')")
	Page<Agendamento> findAllSchedulingByDate(LocalDate date, Pageable pageable);
	
	
	/*
	 SELECT * FROM AGENDAMENTO_SERVICO AS as 
	  INNER JOIN agendamento ag ON ag.id = as.agendamento_id INNER JOIN servico s
	  ON s.id = as.servico_id WHERE ag.data LIKE '2022-03-18%';
	 */
}
