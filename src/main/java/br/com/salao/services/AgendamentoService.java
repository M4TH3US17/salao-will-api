package br.com.salao.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Agendamento;
import br.com.salao.repositories.AgendamentoRepository;
import br.com.salao.repositories.ServicoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;
	@Autowired
	private ServicoRepository servicoRpository;

	public Page<Agendamento> findByPagination(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Agendamento> findAllSchedulingByDate(LocalDate date, Pageable pageable) {
		return repository.findAllSchedulingByDate(date, pageable);
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
		List<Long> ids = new ArrayList<>();
		obj.getServicos().forEach(x -> {
			ids.add(x.getId());
		});
		obj.setServicos(servicoRpository.findAllById(ids));
		return repository.save(obj);
	}

	@Transactional
	public void deleteById(Long id) {
		if(repository.existsById(id) == false) {
			// ClienteNotFoundException
		}
		repository.deleteById(id);
	}
	
	@Transactional
	public void clearSchedule() {
		repository.clearSchedule();
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
