package br.com.salao.config.email.system;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailSystem {

	@Value("${account.gmail.sender}")
	private String sender;
	@Value("${account.gmail.email}")
	private String email;
	@Value("${account.gmail.password}")
	private String password;

	private Properties properties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.trust", "*");
		prop.put("mail.smtp.starttls", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return prop;
	}

	private Session session() {
		return Session.getInstance(properties(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
	}

	private Address[] sendTo(String to) throws Exception {
		return InternetAddress.parse(to);
	}

	protected Message message(String titleEmail, String bodyEmail, String recipients, boolean sendWithHtml) {
		Message message = new MimeMessage(session());
		try {
			message.setFrom(new InternetAddress(email, sender));
			message.setSubject(titleEmail);
			if (sendWithHtml) {
				message.setContent(bodyEmail, "text/html; charset=utf-8");
			} else {
				message.setText(bodyEmail);
			}
			message.setRecipients(Message.RecipientType.TO, sendTo(recipients));
			Transport.send(message);
		} catch (Exception e) {
			System.err.println("Falha ao enviar email! :/");
		}
		return message;
	}
}
