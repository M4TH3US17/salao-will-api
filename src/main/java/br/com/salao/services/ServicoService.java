package br.com.salao.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Servico;
import br.com.salao.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository repository;
	
	public Page<Servico> findByPagination(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Page<Servico> findAllByCategory(String category, Pageable pageable){
		return repository.findByCategoria(category.toUpperCase(), pageable);
	}
	
	public Page<Servico> findByName(String name, String category, Pageable pageable) {
		return repository.findByNameAndCategoria(name, category, pageable);
	}
	
	public Servico findById(Long id) {
		Servico obj = repository.findById(id).orElse(new Servico());
		return obj;
	}
	
	@Transactional
	public Servico save(Servico obj) {
		return repository.save(obj);
	}
	
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Modifying
	@Transactional
	public Servico update(Long id, Servico obj) {
		repository.findById(id).orElseThrow(null);
		Servico entity = repository.getById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Servico entity, Servico obj) {
		entity.setNome(obj.getNome());
		entity.setPreco(obj.getPreco());
		entity.setImagem(obj.getImagem());
		entity.setDescricao(obj.getDescricao());
		entity.setCategoria(obj.getCategoria());
	}
	
	
}
