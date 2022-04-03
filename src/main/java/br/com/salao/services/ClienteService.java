package br.com.salao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Cliente;
import br.com.salao.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

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

	private void updateData(Cliente entity, Cliente obj) {
		entity.setContato(obj.getContato());
		entity.setEmail(obj.getEmail());
		entity.setSenha(obj.getSenha());
		entity.setFoto(obj.getFoto());
	}
	
}
