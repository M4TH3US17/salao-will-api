package br.com.salao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.salao.entidades.Cliente;
import br.com.salao.services.ClienteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<Cliente>> findByPagination (Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	
	@GetMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<Cliente> findByLogin (@PathVariable String login){
		return ResponseEntity.ok().body(service.findByLogin(login));
	}
	
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cliente> save(@RequestBody Cliente obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	
	@DeleteMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable String login){
		service.deleteByLogin(login);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping(value = "/{login}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Cliente> update(@PathVariable String login, @RequestBody Cliente obj){
		return ResponseEntity.ok().body(service.update(login, obj));
	}
	
	/*@PutMapping(value = "/email-confirm", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> confirmEmail(@RequestParam Integer code){
		return ResponseEntity.ok().body(service.confirmEmail(code));
	}*/
}
