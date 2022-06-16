package br.com.salao.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.entidades.servico.core.model.Servico;
import br.com.salao.entidades.servico.service.ServicoService;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/servicos")
public class ServicoController {

	@Autowired
	private ServicoService service;
	@ApiOperation("Retorna uma lista paginada de serviços do salão.")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<Servico>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("Retorna uma lista de serviços filtrada por categoria.")
	@GetMapping(value = "/categoria/{categoria}")
	public ResponseEntity<Page<Servico>> findAllByCategory(@PathVariable("categoria") String categoria, Pageable pageable){
		return ResponseEntity.ok().body(service.findAllByCategory(categoria, pageable));
	}
	@ApiOperation("Retorna uma lista de serviços filtrada por nome e categoria.")
	@GetMapping(produces = "application/json")
	public ResponseEntity<Page<Servico>> findByNameAndCategory(@RequestParam String name,
			@RequestParam String category, Pageable pageable){
		return ResponseEntity.ok().body(service. findByName(name, category, pageable));
	}
	@ApiOperation("Pesquisa um serviço por id.")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Servico> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	@ApiOperation("Registra um novo serviço.")
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Servico> save(@RequestBody Servico obj){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	@ApiOperation("Deleta um serviço por id")
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("Atualiza serviço.")
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Servico> update (@PathVariable Long id, @RequestBody Servico obj){
		return ResponseEntity.ok().body(service.update(id, obj));
	}
}
