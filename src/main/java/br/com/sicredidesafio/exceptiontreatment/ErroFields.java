package br.com.sicredidesafio.exceptiontreatment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroFields {

    String nome;
    String mensagem;

    public ErroFields(String nome, String mensagem) {
        this.nome = nome;
        this.mensagem = mensagem;
    }
}
