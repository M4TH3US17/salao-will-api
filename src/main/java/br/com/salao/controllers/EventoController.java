package br.com.salao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salao.entidades.Evento;
import br.com.salao.entidades.dto.EventoDTO;
import br.com.salao.services.EventoService;

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
	public ResponseEntity<Evento> resetEventInformation(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.resetEventInformation(id));
	}
}
