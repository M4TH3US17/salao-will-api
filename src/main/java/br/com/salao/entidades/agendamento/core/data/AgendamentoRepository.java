package br.com.salao.entidades.agendamento.core.data;

import java.time.LocalDate;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.agendamento.core.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	@Query("SELECT ag FROM Agendamento ag " 
	        + "JOIN ag.cliente " 
	        + "WHERE ag.data LIKE CONCAT(?1, '%')")
	Page<Agendamento> findAllSchedulingByDate(LocalDate date, Pageable pageable);
	
	@Modifying
	@Query("DELETE FROM Agendamento ag WHERE SUBSTRING(ag.data, 1, 10) < CURRENT_DATE")
	void clearSchedule();
	
}
