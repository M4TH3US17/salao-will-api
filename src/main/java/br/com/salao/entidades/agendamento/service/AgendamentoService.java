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
		return repository.findAll(pageable).map(agendamento -> modelMapper.map(agendamento, AgendamentoDTO.class));
	}

	public Page<AgendamentoDTO> findAllSchedulingByDate(LocalDate date, Pageable pageable) {
		return repository.findAllSchedulingByDate(date, pageable).map(agendamento -> modelMapper.map(agendamento, AgendamentoDTO.class));
	}

	public AgendamentoDTO findById(Long id) {
		Agendamento agendamento = repository.findById(id)
				.orElseThrow(null);
		return modelMapper.map(agendamento, AgendamentoDTO.class);
	}

	@Transactional
	public AgendamentoDTO save(Agendamento obj) {
		obj.setServicos(servicoRepository.findAllById(obj.getServicos().stream().map(servico -> servico.getId())
				.collect(Collectors.toList())));

		return modelMapper.map(repository.save(obj), AgendamentoDTO.class);
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
	public AgendamentoDTO update(Long id, Agendamento obj) {
		Agendamento agendamento = repository.findById(id)
				.orElseThrow(null);
		agendamento.setServicos(obj.getServicos());
		agendamento.setData(obj.getData());
		
		return modelMapper.map(repository.save(agendamento), AgendamentoDTO.class);
	}

}
