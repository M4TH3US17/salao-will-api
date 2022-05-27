package br.com.salao.services;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.salao.config.email.*;
import br.com.salao.config.email.system.EmailSystemFactoryService;
import br.com.salao.config.security.impl.ImplementationUserDetailsService;
import br.com.salao.config.security.jwt.JwtService;
import br.com.salao.config.security.jwt.dto.TokenDTO;
import br.com.salao.controllers.exceptions.PasswordInvalidException;
import br.com.salao.entidades.Cliente;
import br.com.salao.entidades.dto.*;
import br.com.salao.repositories.*;
import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class ClienteService {

	private ClienteRepository repository;
	@Qualifier("encoder")
	private PasswordEncoder encoder;
	private RoleRepository roleRepository;
	private EmailClienteRepository emailClienteRepository;
	private ImplementationUserDetailsService userDetailsImpl;

	private EmailSystemFactoryService emailSystemFactory;
	private JwtService jwtService;

	public Page<ClienteDTO> findByPagination(Pageable pageable) {
		Page<Cliente> list = repository.findAll(pageable);
		Page<ClienteDTO> costumers = list.map(x -> new ClienteDTO(x));
		return costumers;
	}

	public ClienteDTO findByLogin(String login) {
		if (repository.existsById(login) == false) {

		}
		return new ClienteDTO(repository.getById(login));
	}

	@Transactional
	public ClienteDTO save(Cliente obj) {
		if (repository.existsById(obj.getLogin())) {
			// lançar um erro informando que já existe um usuário com o login informado
		}
		obj.setSenha(encoder.encode(obj.getSenha()));
		obj.getRoles().add(roleRepository.findRoleByNome("ROLE_USER"));

		if (obj.getEmailCliente() != null) {
			EmailCliente email = obj.getEmailCliente();
			randomCodeGenerator(email, false);
			emailSystemFactory.confirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(),
					obj.getLogin());
		}

		return new ClienteDTO(repository.save(obj));
	}

	@Transactional
	public void deleteByLogin(String login) {
		if (repository.existsById(login) == false) {
			// ClienteNotFoundException
		}
		repository.deleteById(login);
	}

	@Modifying
	@Transactional
	public ClienteDTO update(String login, Cliente obj) {
		if (repository.existsById(login) == false) {

		}
		Cliente entity = repository.getById(login);
		updateData(entity, obj);
		return new ClienteDTO(repository.save(entity));
	}

	public String confirmEmail(String login, ConfirmationEmailDTO code) {
		Cliente obj = repository.getById(login);

		if (obj.getEmailCliente().getConfirmationCode().intValue() == code.getCode().intValue()) {
			obj.getEmailCliente().setConfirmed(true);
			repository.save(obj);
			return "Email Confirmado";

		} else {
			return "Código Incorreto";
		}
	}

	public TokenDTO authenticate(Cliente obj) throws PasswordInvalidException {
		UserDetails client = userDetailsImpl.loadUserByUsername(obj.getLogin());
		boolean validationPassword = encoder.matches(obj.getPassword(), client.getPassword());

		if (validationPassword) {
			
			try {
				Cliente object = repository.getById(obj.getLogin());
				return new TokenDTO(object.getLogin(), jwtService.generationToken(object));
				
			} catch (UsernameNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cheque suas credenciais (login ou senha.)");
			}
			
		}
		throw new PasswordInvalidException("Password Invalid");
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setContato(obj.getContato());
		entity.setSenha(obj.getSenha());
		entity.setFoto(obj.getFoto());

		if (obj.getEmailCliente() != null) {
			if (entity.getEmailCliente().getEmail() != null) {
				emailClienteRepository.deleteById(entity.getEmailCliente().getEmail());
			}
			;
			EmailCliente email = obj.getEmailCliente();
			randomCodeGenerator(email, false);
			entity.setEmailCliente(email);
			emailSystemFactory.confirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(),
					obj.getLogin());
		}
	}

	private void randomCodeGenerator(EmailCliente email, boolean confirmed) {
		email.setConfirmed(confirmed);
		email.setConfirmationCode(new Random().nextInt(100000));
		emailClienteRepository.save(email);
	}
}
