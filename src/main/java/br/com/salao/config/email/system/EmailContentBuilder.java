package br.com.salao.config.email.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Slf4j
public class EmailContentBuilder {

	private Context contextThymeleaf;
	@Autowired
    private final ITemplateEngine templateEngine;

	private final EmailBuilder emailBuilder;
    
    public void buildConfirmationEmail(String recipient, Integer code, String user) {
    	contextThymeleaf = new Context();
    	
    	contextThymeleaf.setVariable("usuario", user);
    	contextThymeleaf.setVariable("codigoDeConfirmacao", code);
    	String message = templateEngine.process("emailConfirmacao", contextThymeleaf);

		emailBuilder.send("Email de Confirmação", message, recipient, true);
		log.info("E-mail de confirmação foi enviado");
    }
	
    public void buildAlertEventEmail(String recipient, String premio, String user) {
    	contextThymeleaf = new Context();
    	
    	contextThymeleaf.setVariable("premio", premio);
    	contextThymeleaf.setVariable("usuario", user);
    	contextThymeleaf.setVariable("contatoDoSalao", "(00) 0000-0000");
		String message = templateEngine.process("alertaDeEvento", contextThymeleaf);

		emailBuilder.send("Evento Salão", message, recipient, true);
		log.info("E-mail de evento foi enviado");
    }
}
