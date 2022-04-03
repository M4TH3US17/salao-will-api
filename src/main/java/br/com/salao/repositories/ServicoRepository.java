package br.com.salao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	@Query("SELECT servico FROM Servico servico "
			+ "WHERE UPPER(servico.categoria) LIKE UPPER(CONCAT('%', TRIM(?1),'%'))")
	Page<Servico> findAllByCategory(String category, Pageable pageable);
	
	@Query("SELECT s FROM Servico s "
			+ "WHERE UPPER(s.nome) LIKE UPPER(CONCAT('%', TRIM(?1),'%')) "
			+ "AND "
			+ "UPPER(s.categoria) LIKE UPPER(CONCAT('%', TRIM(?2),'%'))")
	Page<Servico> findByNameAndCategory(String name, String category, Pageable pageable);
}
