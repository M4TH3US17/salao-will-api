package br.com.salao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.salao.config.security.jwt.dto.*;
import br.com.salao.entidades.Cliente;
import br.com.salao.entidades.dto.*;
import br.com.salao.services.ClienteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<ClienteDTO>> findByPagination (Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	
	@GetMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<ClienteDTO> findByLogin (@PathVariable String login){
		return ResponseEntity.ok().body(service.findByLogin(login));
	}
	
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> save(@RequestBody Cliente obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	
	@DeleteMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable String login){
		service.deleteByLogin(login);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping(value = "/{login}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> update(@PathVariable String login, @RequestBody Cliente obj){
		return ResponseEntity.ok().body(service.update(login, obj));
	}
	
	@PutMapping(value = "/confirmar/{user}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> confirmEmail(@PathVariable("user") String user, 
			@RequestBody ConfirmationEmailDTO code){
		return ResponseEntity.ok().body(service.confirmEmail(user, code));
	}
	
	@PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDTO credentials) throws Exception {
		return ResponseEntity.ok(service.authenticate(new Cliente(credentials.getLogin(), credentials.getSenha())));
	} 
}
