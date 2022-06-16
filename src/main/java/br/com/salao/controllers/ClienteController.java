package br.com.salao.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

import br.com.salao.config.security.jwt.dto.CredentialsDTO;
import br.com.salao.config.security.jwt.dto.TokenDTO;
import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.cliente.core.model.dto.ClienteDTO;
import br.com.salao.entidades.cliente.service.ClienteService;
import br.com.salao.entidades.email.core.model.dto.ConfirmationEmailDTO;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@ApiOperation("Retorna uma lista paginada de clientes.")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<ClienteDTO>> findByPagination (Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("Pesquisa um cliente pelo login (nome).")
	@GetMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<ClienteDTO> findByLogin (@PathVariable String login){
		return ResponseEntity.ok().body(service.findByLogin(login));
	}
	@ApiOperation("Cadastra/salva um novo cliente.")
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> save(@RequestBody Cliente obj) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	@ApiOperation("Deleta um cliente através do login.")
	@DeleteMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable String login){
		service.deleteByLogin(login);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("Atualiza um determinado cliente.")
	@PutMapping(value = "/{login}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> update(@PathVariable String login, @RequestBody Cliente obj){
		return ResponseEntity.ok().body(service.update(login, obj));
	}
	@ApiOperation("Realiza a confirmação de email do cliente. Deverá ser passado o código de confirmação.")
	@PutMapping(value = "/confirmar/{user}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> confirmEmail(@PathVariable("user") String user, 
			@RequestBody ConfirmationEmailDTO code){
		return ResponseEntity.ok().body(service.confirmEmail(user, code));
	}
	@ApiOperation("Realiza a autenticação de usuário. Informe o login e senha.")
	@PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody CredentialsDTO credentials) throws Exception {
		return ResponseEntity.ok(service.authenticate(new Cliente(credentials.getLogin(), credentials.getSenha())));
	} 
}
