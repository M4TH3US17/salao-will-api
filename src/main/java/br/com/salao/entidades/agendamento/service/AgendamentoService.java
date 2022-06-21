package br.com.salao.entidades.agendamento.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.agendamento.core.data.AgendamentoRepository;
import br.com.salao.entidades.agendamento.core.model.Agendamento;
import br.com.salao.entidades.agendamento.core.model.dto.AgendamentoDTO;
import br.com.salao.entidades.servico.core.data.ServicoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ModelMapper modelMapper;

	public Page<AgendamentoDTO> findByPagination(Pageable pageable) {
		return repository.findAll(pageable)
				.map(obj -> modelMapper.map(obj, AgendamentoDTO.class));
	}

	public Page<AgendamentoDTO> findAllSchedulingByDate(LocalDate date, Pageable pageable) {
		return repository.findAllSchedulingByDate(date, pageable)
				.map(obj -> modelMapper.map(obj, AgendamentoDTO.class));
	}

	public AgendamentoDTO findById(Long id) {
		return repository.findById(id).map(obj -> modelMapper.map(obj, AgendamentoDTO.class))
				.orElseThrow(null);
	}

	@Transactional
	public AgendamentoDTO save(AgendamentoDTO agendamento) {
		Agendamento entity = modelMapper.map(agendamento, Agendamento.class);
		entity.setServicos(servicoRepository
				.findAllById(entity.getServicos().stream().map(servico -> servico.getId())
				.collect(Collectors.toList())));

		return modelMapper.map(repository.save(entity), AgendamentoDTO.class);
	}

	@Transactional
	public void deleteById(Long id) {
		repository.findById(id).orElseThrow(null);
		repository.deleteById(id);
	}

	@Transactional
	public void clearSchedule() {
		repository.clearSchedule();
	}

	@Modifying
	@Transactional
	public AgendamentoDTO update(Long id, AgendamentoDTO agendamento) {
		Agendamento obj = repository.findById(id).orElseThrow(null);
		obj.setServicos(agendamento.getServicos());
		obj.setData(agendamento.getData());
		
		return modelMapper.map(repository.save(obj), AgendamentoDTO.class);
	}

}
