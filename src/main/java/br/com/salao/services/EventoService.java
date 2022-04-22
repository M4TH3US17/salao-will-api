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
		entity.setNome(event.getNome());
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
		Evento randomCostomerEvent = findById(2L);
		randomCostomerEvent.setParticipantes(clienteRepository.findAll());
		randomCostomerEvent.setPremio(prizeDraw());
		randomCostomerEvent.setGanhador(costumerDraw());

		return update(2L, randomCostomerEvent);
	}
	
	/* métodos auxiliares*/
	public Servico prizeDraw() {
		List<Servico> premio = servicoRepository.findAll();
		Collections.shuffle(premio);
		return premio.get(0);
	}
	public Cliente costumerDraw() {
		List<Cliente> ganhador = clienteRepository.findAll();
		Collections.shuffle(ganhador);
		return ganhador.get(0);
	}
}
