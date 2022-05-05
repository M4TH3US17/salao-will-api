package br.com.salao.services;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.email.EmailClienteRepository;
import br.com.salao.email.system.EmailSystemFactoryService;
import br.com.salao.entidades.Cliente;
import br.com.salao.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private EmailClienteRepository emailClienteRepository;
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
		if(obj.getEmailCliente() != null) {
			int confirmationCode = new Random().nextInt(100000);
			
			obj.getEmailCliente().setConfirmationCode(confirmationCode);
			obj.getEmailCliente().setConfirmed(false);
			emailClienteRepository.save(obj.getEmailCliente());
			
			emailSystemFactory.confirmationEmail(obj.getEmailCliente().getEmail(), confirmationCode, obj.getLogin());
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
	
	/*public String confirmEmail(Integer codeConfirmation) {
		return "confirmado";
	}*/

	private void updateData(Cliente entity, Cliente obj) {
		entity.setContato(obj.getContato());
		entity.setEmailCliente(obj.getEmailCliente());
		entity.setSenha(obj.getSenha());
		entity.setFoto(obj.getFoto());
	}
}
