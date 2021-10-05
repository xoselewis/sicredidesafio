package br.com.sicredidesafio.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "votacao")
public class Votacao extends Entidad {

    /*O campo voto poderia ser usado um boolean e
     *tratada a entrada sim e não no controller ou,
     * assim como com um enum para ajudar
     */

    @Column(length = 3)
    @Size(min = 3, max = 3)
    private String voto;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "sessao_id")
    private Sessao sessao;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "associado_id")
    private Associado associado;

}