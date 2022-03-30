package br.com.salao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salao.services.ChatService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ChatController {

	@Autowired
	private ChatService service;
}
