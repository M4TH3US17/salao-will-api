package br.com.salao.controllers;

import java.time.LocalDate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:utils/documentation.properties")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;
	@ApiOperation("${agendamento.docs.findByPagination}")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("${agendamento.docs.findAllSchedulingByDate}")
	@GetMapping(value = "/filtro", produces = "application/json")
	public ResponseEntity<Page<AgendamentoDTO>> findAllSchedulingByDate(
			@ApiParam("${agendamento.docs.findAllSchedulingByDate.param}")
		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, Pageable pageable) {
		return ResponseEntity.ok().body(service.findAllSchedulingByDate(data, pageable));
	}
	@ApiOperation("${agendamento.docs.findById}")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok().body(service.findById(id));
	}
	@ApiOperation("${agendamento.docs.save}")
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> save(@RequestBody AgendamentoDTO agendamento) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(agendamento));
	}
	@ApiOperation("${agendamento.docs.deleteById}")
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("${agendamento.docs.clearSchedule}")
	@DeleteMapping(value = "/limpar-agenda")
	public ResponseEntity<Void> clearSchedule(){
		service.clearSchedule();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("${agendamento.docs.update}")
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody AgendamentoDTO agendamento)
	throws Exception {
		return ResponseEntity.ok().body(service.update(id, agendamento));
	}
}
