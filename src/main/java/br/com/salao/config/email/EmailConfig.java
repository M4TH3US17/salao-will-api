package br.com.salao.config.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

	@Bean
	public Email email() {
		return new Email();
	}
}
