package br.com.sicredidesafio.controller;

import br.com.sicredidesafio.MensageriaFila.ResultadoVotacaoSender;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagens")
public class TesteEnvioController {

    @Autowired
    private ResultadoVotacaoSender resultadoVotacaoSender;

    @PostMapping("/v1/mensage")
    @Hidden
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@RequestBody String mensagem) {

        resultadoVotacaoSender.send(mensagem);

        return   "O mensagem enviado: " + mensagem;
    }


}
