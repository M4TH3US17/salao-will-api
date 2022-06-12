package br.com.salao.config.email.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class EmailContentBuilder {

	private Context contextThymeleaf;
	@Autowired
    private final ITemplateEngine templateEngine;
    
    @Autowired
    private EmailSystem email;
    
    public void buildConfirmationEmail(String recipient, Integer code, String user) {
    	contextThymeleaf = new Context();
    	
    	contextThymeleaf.setVariable("usuario", user);
    	contextThymeleaf.setVariable("codigoDeConfirmacao", code);
    	String message = templateEngine.process("emailConfirmacao", contextThymeleaf);
    	
    	email.send("Email de Confirmação", message, recipient, true);
    }
	
    public void buildAlertEventEmail(String recipient, String premio, String user) {
    	contextThymeleaf = new Context();
    	
    	contextThymeleaf.setVariable("premio", premio);
    	contextThymeleaf.setVariable("usuario", user);
    	contextThymeleaf.setVariable("contatoDoSalao", "(00) 0000-0000");
    	String message = templateEngine.process("alertaDeEvento", contextThymeleaf);
    	
    	email.send("Evento Salão", message, recipient, true);
    }
}
