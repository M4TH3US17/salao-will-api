package br.com.salao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Habilita nosso servidor WebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	/*Registra um ponto de extremidade websocket que os frontend usará para se conectar ao servidor.*/
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").withSockJS();
    }
	
	/*Configura nosso agente de mensagens que será usado para rotear mensagens de um browse pro outro.*/
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
