package br.com.salao.controllers;

import java.time.LocalDate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.agendamento.core.model.Agendamento;
import br.com.salao.entidades.agendamento.core.model.dto.AgendamentoDTO;
import br.com.salao.entidades.agendamento.service.AgendamentoService;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	@ApiOperation("Retorna uma lista paginada de agendamentos.")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("Retorna uma lista de agendamentos de uma determinada data (yyyy-MM-dd).")
	@GetMapping(value = "/filtro", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findAllSchedulingByDate(
		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		return ResponseEntity.ok().body(service.findAllSchedulingByDate(data, pageable));
	}
	@ApiOperation("Pesquisa agendamento por id.")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	@ApiOperation("Aqui o cliente registrará um agendamento.")
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> save(@RequestBody Agendamento obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	@ApiOperation("Deleta um agendamento por id.")
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("Limpa a agenda automaticamente (apaga somente os agendamentos de datas passadas.)")
	@DeleteMapping(value = "/limpar-agenda")
	public ResponseEntity<Void> clearSchedule(){
		service.clearSchedule();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("Atualiza agendamento.")
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody Agendamento obj){
		return ResponseEntity.ok().body(service.update(id, obj));
	}
}
