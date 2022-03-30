package br.com.salao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.salao.repositories.ChatRepository;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepository repository;

}
