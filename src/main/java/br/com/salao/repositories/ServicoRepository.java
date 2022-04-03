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
}
