package br.com.salao.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.evento.core.model.Evento;
import br.com.salao.entidades.evento.core.model.dto.EventoDTO;
import br.com.salao.entidades.evento.service.EventoService;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/eventos")
public class EventoController {

	@Autowired
	private EventoService service;
	@ApiOperation("Retorna uma lista paginada de eventos.")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<EventoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(service.findByPagination(pageable));
	}
	@ApiOperation("Busca um evento por id.")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> findById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	@ApiOperation("Este evento sorteia um ganhador e um prêmio aleatório. " +
			"O vencedor será acionado no email que foi sorteado.")
	@GetMapping(value = "/cliente-aleatorio", produces = "application/json")
	public ResponseEntity<EventoDTO> randomCostomerEvent(){
		return ResponseEntity.status(HttpStatus.OK).body(service.randomCostomerEvent());
	}
	@ApiOperation("Atualiza as informações de um determinado evento.")
	@PutMapping(value = "/atualizar/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<EventoDTO> update(@PathVariable("id") Long id, @RequestBody Evento event){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, event));
	}
	@ApiOperation("Restaura o evento, ou seja, apaga as informações.")
	@PutMapping(value = "/resetar/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> resetEventInformation(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.resetEventInformation(id));
	}
}
