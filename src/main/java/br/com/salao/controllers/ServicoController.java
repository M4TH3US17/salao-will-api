package br.com.salao.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.servico.core.model.Servico;
import br.com.salao.entidades.servico.service.ServicoService;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/servicos")
@PropertySource("classpath:utils/documentation.properties")
public class ServicoController {

	@Autowired
	private ServicoService service;
	@ApiOperation("${servico.docs.findByPagination}")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<Servico>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("${servico.docs.findAllByCategory}")
	@GetMapping(value = "/categoria/{categoria}")
	public ResponseEntity<Page<Servico>> findAllByCategory(@PathVariable("categoria") String categoria, Pageable pageable){
		return ResponseEntity.ok().body(service.findAllByCategory(categoria, pageable));
	}
	@ApiOperation("${servico.docs.findByNameAndCategory}")
	@GetMapping(produces = "application/json")
	public ResponseEntity<Page<Servico>> findByNameAndCategory(@RequestParam String name,
			@RequestParam String category, Pageable pageable){
		return ResponseEntity.ok().body(service. findByName(name, category, pageable));
	}
	@ApiOperation("${servico.docs.findById}")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Servico> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	@ApiOperation("${servico.docs.save}")
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Servico> save(@RequestBody Servico servico){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servico));
	}
	@ApiOperation("${servico.docs.deleteById}")
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("${servico.docs.update}")
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico servico){
		return ResponseEntity.ok().body(service.update(id, servico));
	}
}
