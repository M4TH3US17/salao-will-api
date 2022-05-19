package br.com.salao.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.salao.email.system.EmailSystemFactoryService;
import br.com.salao.entidades.Cliente;
import br.com.salao.entidades.Evento;
import br.com.salao.entidades.Servico;
import br.com.salao.entidades.dto.EventoDTO;
import br.com.salao.repositories.ClienteRepository;
import br.com.salao.repositories.EventoRepository;
import br.com.salao.repositories.ServicoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private EmailSystemFactoryService emailSystemFactoryService;

	public Page<EventoDTO> findByPagination(Pageable pageable) {
		Page<Evento> list = repository.findAll(pageable);
		return list.map(x -> new EventoDTO(x, x.getGanhador(), x.getParticipantes()));
	}

	public EventoDTO findById(Long id) {
		if (repository.existsById(id) == false) {

		}
		Evento evento = repository.findById(id).get();
		
		return new EventoDTO(evento, evento.getGanhador(), evento.getParticipantes());
	}

	public EventoDTO update(Long id, Evento event) {
		if (repository.existsById(id) == false) {

		}
		Evento entity = repository.getById(id);
		updateData(entity, event);
		Evento evento = repository.save(entity);
		
		return new EventoDTO(evento, evento.getGanhador(), evento.getParticipantes());
	}

	private void updateData(Evento entity, Evento event) {
		entity.setNome(event.getNome());
		entity.setData(event.getData());
		entity.setImagem(event.getImagem());
		entity.setDescricao(event.getDescricao());
	}

	public Evento resetEventInformation(Long id) {
		if (repository.existsById(id) == false) {

		}
		Evento evento = repository.getById(id);
		evento.setParticipantes(new ArrayList<>());
		evento.setGanhador(null);
		evento.setPremio(null);
		return repository.save(evento);
	}

	/* EVENTOS */
	public EventoDTO randomCostomerEvent() {
		Evento randomCostomerEvent = repository.getById(2L);
		randomCostomerEvent.setParticipantes(clienteRepository.findAll());
		
		randomCostomerEvent.setPremio(prizeDraw());
		Cliente cliente = costumerDraw();
		randomCostomerEvent.setGanhador(cliente);
		
		if(cliente.getEmailCliente().getEmail() != null && cliente.getEmailCliente().getConfirmed() == true) {
			emailSystemFactoryService.alertEventEmail(
					cliente.getEmailCliente().getEmail(), 
					randomCostomerEvent.getPremio().getNome().toLowerCase(), 
					cliente.getLogin());
		}

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
