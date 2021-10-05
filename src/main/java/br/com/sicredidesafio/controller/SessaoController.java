package br.com.sicredidesafio.controller;

import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.repository.SessaoRepository;
import br.com.sicredidesafio.service.SessaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/sessoes")
@EnableAsync
public class SessaoController {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    SessaoService sessaoService;

    //***Requerimento solicitado no desafio***
    @PostMapping("/v1/cadastrarsessao")
    @ResponseStatus(HttpStatus.CREATED)
    public Sessao cadastrar(@Valid @RequestBody Sessao sessao) {
        log.info("Sessão de votação Iniciada");
        Sessao sessaoArmazenada =  sessaoService.cadastrar(sessao);
        sessaoService.fechaSessaoVotacao(sessaoArmazenada);
        return sessaoArmazenada;
    }

    /* Nota: A implementação de vários EndPoints podem ser criados acompanhando a lógica de poder ter a
     * sessão os status de Criada, Aberta e Fechada, não foram desenvolvidos no desafio
     * para evitar Over Engineering, pelo fato de não ter contato com cliente e conhecer todas as logica do negócio...
     *
     * Os EndPoint como:
     *  -> definirtempduracao
     *  -> alteracaossessao
     *  -> eliminarsessao
     *  Entre outros que poderiam ser criados não foram incluidos para evitar  Over Engineering
     */

}
