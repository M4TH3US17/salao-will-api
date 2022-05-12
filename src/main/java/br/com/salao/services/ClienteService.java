package br.com.salao.services;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.salao.email.EmailCliente;
import br.com.salao.email.EmailClienteRepository;
import br.com.salao.email.system.EmailSystemFactoryService;
import br.com.salao.entidades.Cliente;
import br.com.salao.entidades.dto.ConfirmationEmailDTO;
import br.com.salao.repositories.ClienteRepository;
import br.com.salao.repositories.RoleRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EmailClienteRepository emailClienteRepository;
	@Autowired
	@Qualifier("encoder")
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmailSystemFactoryService emailSystemFactory;

	public Page<Cliente> findByPagination(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Cliente findByLogin(String login) {
		if (repository.existsById(login) == false) {

		}
		return repository.getById(login);
	}

	@Transactional
	public Cliente save(Cliente obj) {
		if(repository.existsById(obj.getLogin())) {
			// lançar um erro informando que já existe um usuário com o login informado
		}
		obj.setSenha(encoder.encode(obj.getSenha()));
		obj.getRoles().add(roleRepository.findRoleByNome("ROLE_USER"));
		
		if(obj.getEmailCliente() != null) {
			EmailCliente email = obj.getEmailCliente();
			randomCodeGenerator(email, false);
			emailSystemFactory.confirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(), obj.getLogin());
		}
		
		return repository.save(obj);
	}

	@Transactional
	public void deleteByLogin(String login) {
		if(repository.existsById(login) == false) {
			// ClienteNotFoundException
		}
		repository.deleteById(login);
	}

	@Modifying
	@Transactional
	public Cliente update(String login, Cliente obj) {
		if (repository.existsById(login) == false) {

		}
		Cliente entity = repository.getById(login);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	public String confirmEmail(String login, ConfirmationEmailDTO code) {
		Cliente obj = repository.getById(login);
		
		if(obj.getEmailCliente().getConfirmationCode().intValue() == code.getCode().intValue()) {
			obj.getEmailCliente().setConfirmed(true);
			repository.save(obj);
			return "Email Confirmado";
			
		} else {
			return "Código Incorreto";
		}
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setContato(obj.getContato());
		entity.setSenha(obj.getSenha());
		entity.setFoto(obj.getFoto());
		
		if(obj.getEmailCliente() != null) {
			if(entity.getEmailCliente().getEmail() != null) {
				emailClienteRepository.deleteById(entity.getEmailCliente().getEmail());
			};
			EmailCliente email = obj.getEmailCliente();
			randomCodeGenerator(email, false);
			entity.setEmailCliente(email);
			emailSystemFactory.confirmationEmail(obj.getEmailCliente().getEmail(), email.getConfirmationCode(), obj.getLogin());
		}
	}
	
	private void randomCodeGenerator(EmailCliente email, boolean confirmed) {
		email.setConfirmed(confirmed);
		email.setConfirmationCode(new Random().nextInt(100000));
		emailClienteRepository.save(email);
	}
}
