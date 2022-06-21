package br.com.salao.controllers;

import br.com.salao.entidades.cliente.core.model.dto.ModifyClienteDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:utils/documentation.properties")
public class ClienteController {
	
	@Autowired
	private ClienteService service;

	@ApiOperation("${cliente.docs.findByPagination}")
	@GetMapping(value = "/pagination", produces = "application/json")
	public ResponseEntity<Page<ClienteDTO>> findByPagination(Pageable pageable){
		return ResponseEntity.ok().body(service.findByPagination(pageable));
	}
	@ApiOperation("${cliente.docs.findByLogin}")
	@GetMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<ClienteDTO> findByLogin(@PathVariable String login) throws Exception {
		return ResponseEntity.ok().body(service.findByLogin(login));
	}
	@ApiOperation("${cliente.docs.save}")
	@PostMapping(value = "/cadastro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> save(@ApiParam("${cliente.docs.save.param}") @RequestBody ModifyClienteDTO obj)
			throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(obj));
	}
	@ApiOperation("${cliente.docs.deleteByLogin}")
	@DeleteMapping(value = "/{login}", produces = "application/json")
	public ResponseEntity<Void> deleteById(@PathVariable String login) throws Exception {
		service.deleteByLogin(login);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	@ApiOperation("${cliente.docs.update}")
	@PutMapping(value = "/{login}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ClienteDTO> update(@PathVariable String login,
											 @RequestBody ModifyClienteDTO obj) throws Exception {
		return ResponseEntity.ok().body(service.update(login, obj));
	}
	@ApiOperation("${cliente.docs.confirmEmail}")
	@PutMapping(value = "/confirmar/{login}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> confirmEmail(@PathVariable("login") String login,
											   @ApiParam("${cliente.docs.confirmEmail.param}")
											   @RequestBody ConfirmationEmailDTO code) throws Exception {
		return ResponseEntity.ok().body(service.confirmEmail(login, code));
	}
	@ApiOperation("${cliente.docs.auth}")
	@PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
	public ResponseEntity<TokenDTO> authenticate(@ApiParam("${cliente.docs.auth.param}") @RequestBody CredentialsDTO credentials)
			throws Exception {
		return ResponseEntity.ok(service.authenticate(new Cliente(credentials.getLogin(), credentials.getSenha())));
	} 
}
