package br.com.salao.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salao.entidades.Agendamento;
import br.com.salao.entidades.dto.AgendamentoDTO;
import br.com.salao.services.AgendamentoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	
	@GetMapping(value = "/filtro", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findAllSchedulingByDate(
		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		return ResponseEntity.ok().body(service.findAllSchedulingByDate(data, pageable));
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> save(@RequestBody Agendamento obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping(value = "/limpar-agenda")
	public ResponseEntity<Void> clearSchedule(){
		service.clearSchedule();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody Agendamento obj){
		return ResponseEntity.ok().body(service.update(id, obj));
	}
}
