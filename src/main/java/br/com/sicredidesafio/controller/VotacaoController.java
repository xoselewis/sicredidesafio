package br.com.sicredidesafio.controller;

import br.com.sicredidesafio.domain.Votacao;
import br.com.sicredidesafio.domain.repository.VotacaoRepository;
import br.com.sicredidesafio.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/votacoes")
public class VotacaoController {

    @Autowired
    VotacaoRepository votacaoRepository;

    @Autowired
    VotacaoService votacaoService ;

    //***Requerimento solicitado no desafio***
    @PostMapping("/v1/recebervoto")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrar(@Valid @RequestBody Votacao votacao) {
        votacaoService.cadastrar(votacao);
        return  "Votação com Sucesso!";
    }

    /* Nota: A implementação de vários EndPoints como:
     *  -> eliminarvoto
     *  -> trocarvoto
     *  -> eliminarsessao
     *  Entre outros que poderiam ser criados não foram incluidos para evitar Over Engineering
     * ao não ter especificações para serem criados no desafio
     */

}
