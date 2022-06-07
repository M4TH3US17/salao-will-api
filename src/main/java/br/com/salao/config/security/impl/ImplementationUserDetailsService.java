package br.com.salao.config.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.com.salao.entidades.Cliente;
import br.com.salao.repositories.ClienteRepository;

@Service
public class ImplementationUserDetailsService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente usuario = clienteRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities());
	}
}