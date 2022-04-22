package br.com.salao.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.*;
import br.com.salao.repositories.*;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ServicoRepository servicoRepository;

	public Page<Evento> findByPagination(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Evento findById(Long id) {
		if (repository.existsById(id) == false) {

		}
		return repository.findById(id).get();
	}

	public Evento update(Long id, Evento event) {
		Evento entity = findById(id);
		updateData(entity, event);
		return repository.save(entity);
	}

	private void updateData(Evento entity, Evento event) {
		entity.setData(event.getData());
		entity.setImagem(event.getImagem());
		entity.setDescricao(event.getDescricao());
	}

	public Evento resetEventInformation(Long id) {
		Evento evento = findById(id);
		evento.setParticipantes(new ArrayList<>());
		evento.setGanhador(null);
		evento.setPremio(null);
		return repository.save(evento);
	}

	/* EVENTOS */
	public Evento randomCostomerEvent() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<Servico> servicos = servicoRepository.findAll();
		Evento randomCostomerEvent = findById(2L);

		randomCostomerEvent.setParticipantes(clientes);

		Collections.shuffle(clientes);
		randomCostomerEvent.setGanhador(clientes.get(0));

		Collections.shuffle(servicos);
		randomCostomerEvent.setPremio(servicos.get(0));

		return update(2L, randomCostomerEvent);
	}
}
