package br.com.salao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Agendamento;
import br.com.salao.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;

	public Page<Agendamento> findByPagination(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Agendamento findById(Long id) {
		if (repository.existsById(id) == false) {

		}
		return repository.getById(id);
	}

	@Transactional
	public Agendamento save(Agendamento obj) {
		/*if(repository.existsById(obj.getId())) {
			// lançar um erro informando que já existe um usuário com o login informado
		}*/
		return repository.save(obj);
	}

	@Transactional
	public void deleteById(Long id) {
		if(repository.existsById(id) == false) {
			// ClienteNotFoundException
		}
		repository.deleteById(id);
	}

	@Modifying
	@Transactional
	public Agendamento update(Long id, Agendamento obj) {
		if (repository.existsById(id) == false) {

		}
		Agendamento entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Agendamento entity, Agendamento obj) {
		entity.setServicos(obj.getServicos());
		entity.setData(obj.getData());
	}
	
}
