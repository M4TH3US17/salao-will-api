package br.com.salao.entidades.evento.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.salao.config.email.system.EmailContentBuilder;
import br.com.salao.entidades.cliente.core.data.ClienteRepository;
import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.evento.core.data.EventoRepository;
import br.com.salao.entidades.evento.core.model.Evento;
import br.com.salao.entidades.evento.core.model.dto.EventoDTO;
import br.com.salao.entidades.servico.core.data.ServicoRepository;
import br.com.salao.entidades.servico.core.model.Servico;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private EmailContentBuilder buildEmail;
	@Autowired
	private ModelMapper mapper;

	public Page<EventoDTO> findByPagination(Pageable pageable) {
		return repository.findAll(pageable).map(evento -> mapper.map(evento, EventoDTO.class));
	}

	public EventoDTO findById(Long id) {
		Evento evento = repository.findById(id)
				.orElseThrow(null);
		return mapper.map(evento, EventoDTO.class);
	}

	public EventoDTO update(Long id, Evento event) {
		Evento entity = repository.findById(id).orElseThrow(null);
		updateData(entity, event);
		
		return mapper.map(repository.save(entity), EventoDTO.class);
	}

	private void updateData(Evento entity, Evento event) {
		entity.setNome(event.getNome());
		entity.setData(event.getData());
		entity.setImagem(event.getImagem());
		entity.setDescricao(event.getDescricao());
	}

	public EventoDTO resetEventInformation(Long id) {
		Evento evento = repository.findById(id).orElseThrow(null);
		evento.setParticipantes(new ArrayList<>());
		evento.setGanhador(null);
		evento.setPremio(null);
		return mapper.map(repository.save(evento), EventoDTO.class);
	}

	/* EVENTOS */
	public EventoDTO randomCostomerEvent() {
		Evento randomCostomerEvent = repository.getById(2L);
		randomCostomerEvent.setParticipantes(clienteRepository.findAll());
		
		randomCostomerEvent.setPremio(prizeDraw());
		Cliente cliente = costumerDraw();
		randomCostomerEvent.setGanhador(cliente);
		
		if(cliente.getEmailCliente().getEmail() != null && cliente.getEmailCliente().getConfirmed() == true)
			buildEmail.buildAlertEventEmail(cliente.getEmailCliente().getEmail(), 
					randomCostomerEvent.getPremio().getNome().toLowerCase(), cliente.getLogin());

		return mapper.map(update(2L, randomCostomerEvent), EventoDTO.class);
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
