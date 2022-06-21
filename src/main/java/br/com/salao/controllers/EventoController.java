package br.com.salao.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.evento.core.model.dto.EventoDTO;
import br.com.salao.entidades.evento.service.EventoService;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/eventos")
@PropertySource("classpath:utils/documentation.properties")
public class EventoController {

	@Autowired
	private EventoService service;
	@ApiOperation("${evento.docs.findByPagination}")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<EventoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(service.findByPagination(pageable));
	}
	@ApiOperation("${evento.docs.findById}")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> findById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	@ApiOperation("${evento.docs.randomCostomerEvent}")
	@GetMapping(value = "/cliente-aleatorio", produces = "application/json")
	public ResponseEntity<EventoDTO> randomCostomerEvent(){
		return ResponseEntity.status(HttpStatus.OK).body(service.randomCostomerEvent());
	}
	@ApiOperation("${evento.docs.update}")
	@PutMapping(value = "/atualizar/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<EventoDTO> update(@PathVariable("id") Long id, @RequestBody EventoDTO evento){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, evento));
	}
	@ApiOperation("${evento.docs.resetEventInformation}")
	@PutMapping(value = "/resetar/{id}", produces = "application/json")
	public ResponseEntity<EventoDTO> resetEventInformation(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.resetEventInformation(id));
	}
}
