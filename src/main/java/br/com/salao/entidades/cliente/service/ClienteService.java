package br.com.salao.entidades.cliente.service;

import java.util.Random;

import javax.transaction.Transactional;

import br.com.salao.entidades.cliente.core.model.dto.ModifyClienteDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.salao.config.email.system.EmailContentBuilder;
import br.com.salao.config.security.impl.ImplementationUserDetailsService;
import br.com.salao.config.security.jwt.JwtService;
import br.com.salao.config.security.jwt.dto.TokenDTO;
import br.com.salao.entidades.cliente.core.data.ClienteRepository;
import br.com.salao.entidades.cliente.core.data.RoleRepository;
import br.com.salao.entidades.cliente.core.exceptions.PasswordInvalidException;
import br.com.salao.entidades.cliente.core.model.Cliente;
import br.com.salao.entidades.cliente.core.model.dto.ClienteDTO;
import br.com.salao.entidades.email.core.data.EmailClienteRepository;
import br.com.salao.entidades.email.core.model.EmailCliente;
import br.com.salao.entidades.email.core.model.dto.ConfirmationEmailDTO;
import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class ClienteService {

	private ClienteRepository repository;
	@Qualifier("encoder")
	private PasswordEncoder encoder;
	private RoleRepository roleRepository;
	private EmailClienteRepository emailClienteRepository;
	private ImplementationUserDetailsService userDetailsImpl;

	private EmailContentBuilder buildEmail;
	private JwtService jwtService;
	
	@Autowired
	private ModelMapper modelMapper;

	public Page<ClienteDTO> findByPagination(Pageable pageable) {
		return repository.findAll(pageable).map(cliente -> modelMapper.map(cliente, ClienteDTO.class));
	}

	public ClienteDTO findByLogin(String login) {
		Cliente cliente = repository.findById(login).orElseThrow(null);
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	@Transactional
	public ClienteDTO save(ModifyClienteDTO cliente) {
		/*repository.findById(obj.getLogin()).orElseThrow(null);*/
		Cliente obj = modelMapper.map(cliente, Cliente.class);
		obj.setSenha(encoder.encode(obj.getSenha()));
		obj.getRoles().add(roleRepository.findRoleByNome("ROLE_USER"));

		if (obj.getEmailCliente() != null) {
			EmailCliente email = obj.getEmailCliente();
			randomCodeGenerator(email, false);
			buildEmail.buildConfirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(), obj.getLogin());
		}
		
		return modelMapper.map(repository.save(obj), ClienteDTO.class);
	}

	@Transactional
	public void deleteByLogin(String login) {
		repository.findById(login).orElseThrow(null);
		repository.deleteById(login);
	}

	@Modifying
	@Transactional
	public ClienteDTO update(String login, ModifyClienteDTO obj) {
		Cliente entity = repository.findById(login).orElseThrow(null);
		updateData(entity, obj);
		
		return modelMapper.map(repository.save(entity), ClienteDTO.class);
	}

	public String confirmEmail(String login, ConfirmationEmailDTO code) {
		Cliente obj = repository.getById(login);

		if (obj.getEmailCliente().getConfirmationCode().intValue() == code.getCode().intValue()) {
			obj.getEmailCliente().setConfirmed(true);
			repository.save(obj);
			return "Email Confirmado";
		} else 
			return "Código Incorreto";
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

	private void updateData(Cliente entity, ModifyClienteDTO obj) {
		entity.setContato(obj.getContato());
		entity.setSenha(obj.getSenha());
		entity.setFoto(obj.getFoto());

		if (obj.getEmailCliente() != null) {
			if (entity.getEmailCliente().getEmail() != null) {
				emailClienteRepository.deleteById(entity.getEmailCliente().getEmail());
			}
			EmailCliente email = modelMapper.map(obj.getEmailCliente(), EmailCliente.class);
			randomCodeGenerator(email, false);
			entity.setEmailCliente(email);
			buildEmail.buildConfirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(), obj.getLogin());
		}
	}

	private void randomCodeGenerator(EmailCliente email, boolean confirmed) {
		email.setConfirmed(confirmed);
		email.setConfirmationCode(new Random().nextInt(100000));
		emailClienteRepository.save(email);
	}
}
