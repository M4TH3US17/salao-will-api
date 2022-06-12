package br.com.salao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.evento.core.model.Evento;
import br.com.salao.entidades.evento.core.model.dto.EventoDTO;
import br.com.salao.entidades.evento.service.EventoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/eventos")
public class EventoController {

	@Autowired
	private EventoService service;
	
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<EventoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(service.findByPagination(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> findById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@GetMapping(value = "/cliente-aleatorio", produces = "application/json")
	public ResponseEntity<EventoDTO> randomCostomerEvent(){
		return ResponseEntity.status(HttpStatus.OK).body(service.randomCostomerEvent());
	}
	
	@PutMapping(value = "/atualizar/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<EventoDTO> update(@PathVariable("id") Long id, @RequestBody Evento event){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, event));
	}
	
	@PutMapping(value = "/resetar/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> resetEventInformation(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.resetEventInformation(id));
	}
}
