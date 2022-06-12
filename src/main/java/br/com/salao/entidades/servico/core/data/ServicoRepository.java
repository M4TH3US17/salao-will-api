package br.com.salao.entidades.servico.core.data;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.servico.core.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	@Query("SELECT servico FROM Servico servico "
			+ "WHERE UPPER(servico.categoria) LIKE CONCAT('%', ?1,'%')")
	Page<Servico> findByCategoria(String categoria, Pageable pageable);
	
	@Query("SELECT s FROM Servico s "
			+ "WHERE UPPER(s.nome) LIKE UPPER(CONCAT('%', ?1,'%')) "
			+ "AND "
			+ "UPPER(s.categoria) LIKE UPPER(CONCAT('%', ?2,'%'))")
	Page<Servico> findByNameAndCategoria(String name, String category, Pageable pageable);
}
