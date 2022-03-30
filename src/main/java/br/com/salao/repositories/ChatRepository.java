package br.com.salao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.salao.entidades.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

}
