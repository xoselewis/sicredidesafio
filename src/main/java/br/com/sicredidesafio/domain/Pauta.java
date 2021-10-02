package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pauta")
public class Pauta extends Entidad {

    @Column(nullable = false)
    String  descricao;

    @OneToOne(mappedBy="pauta")
    private Sessao sessaodaPauta;

}
