package br.com.sicredidesafio.MensageriaFila;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultadoVotacaoMensagemConfig {

    public static final String NOME_FILA = "FilaVotacao";
    public static final String NOME_EXCHANGE = "ExchangeVotacao";
    public static final String ROUTING_KEY = "criarVotacao";

    @Bean
    DirectExchange usuarioExchange() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(NOME_FILA).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(usuarioExchange()).with(ROUTING_KEY);
    }
}
