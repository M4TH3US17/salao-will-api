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
import br.com.salao.entidades.dto.AgendamentoDTO;
import br.com.salao.repositories.AgendamentoRepository;
import br.com.salao.repositories.ServicoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;
	@Autowired
	private ServicoRepository servicoRpository;

	public Page<AgendamentoDTO> findByPagination(Pageable pageable) {
		Page<Agendamento> list = repository.findAll(pageable);
		return list.map(x -> new AgendamentoDTO(x, x.getCliente()));
	}

	public Page<AgendamentoDTO> findAllSchedulingByDate(LocalDate date, Pageable pageable) {
		Page<Agendamento> list = repository.findAllSchedulingByDate(date, pageable);
		return list.map(x -> new AgendamentoDTO(x, x.getCliente()));
	}
	
	public AgendamentoDTO findById(Long id) {
		if (repository.existsById(id) == false) {

		}
		Agendamento obj = repository.getById(id);
		return new AgendamentoDTO(obj, obj.getCliente());
	}

	@Transactional
	public AgendamentoDTO save(Agendamento obj) {
		/*if(repository.existsById(obj.getId())) {
			// lançar um erro informando que já existe um usuário com o login informado
		}*/
		List<Long> ids = new ArrayList<>();
		obj.getServicos().forEach(x -> {
			ids.add(x.getId());
		});
		obj.setServicos(servicoRpository.findAllById(ids));
		
		Agendamento agendamento = repository.save(obj);
		return new AgendamentoDTO(agendamento, agendamento.getCliente());
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
	public AgendamentoDTO update(Long id, Agendamento obj) {
		if (repository.existsById(id) == false) {

		}
		Agendamento entity = repository.getById(id);
		updateData(entity, obj);
		
		Agendamento agendamento = repository.save(entity);
		return new AgendamentoDTO(agendamento, agendamento.getCliente());
	}

	private void updateData(Agendamento entity, Agendamento obj) {
		entity.setServicos(obj.getServicos());
		entity.setData(obj.getData());
	}
	
}
