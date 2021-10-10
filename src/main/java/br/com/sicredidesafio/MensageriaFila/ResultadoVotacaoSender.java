package br.com.sicredidesafio.MensageriaFila;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultadoVotacaoSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private Queue queue;

    public void send(String resultadaVotacao) {
        rabbitTemplate.convertAndSend(ResultadoVotacaoMensagemConfig.NOME_EXCHANGE, ResultadoVotacaoMensagemConfig.ROUTING_KEY, resultadaVotacao);
    }

}
