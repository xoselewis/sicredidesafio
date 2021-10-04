package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@Table(name = "votacao")
public class Votacao extends Entidad {

    @Column(length = 3)
    @Size(min = 3, max = 3)
    private String voto;

    /*O campo voto poderia ser usado um boolean e
    *tratada a entrada sim e não no controller ou,
    * assim como com um enum para ajudar
    */

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "associado_id")
    private Associado associado;

}