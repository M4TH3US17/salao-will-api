package br.com.salao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.salao.entidades.Chat;
import br.com.salao.entidades.Mensagem;
import br.com.salao.repositories.ChatRepository;
import br.com.salao.repositories.ClienteRepository;
import br.com.salao.repositories.MensagemRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRep;
	@Autowired
	private ChatRepository chatRep;
	@Autowired
	private MensagemRepository msgRep;
	
	static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Chat chat = chatRep.findById(2L).get();
		List<Mensagem> msgs = chat.getMsgs();
		msgs.forEach(x -> {
			System.out.println(chat.getNome()+", "+ x.getMensagem() +", data: " + sdf.format(Date.from(x.getDate()))+", enviado por: " + x.getCliente().getLogin());
		});
	}

}
