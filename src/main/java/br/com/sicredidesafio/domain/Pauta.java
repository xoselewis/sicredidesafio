package br.com.sicredidesafio.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "pauta")
public class Pauta extends Entidad {

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    String descricao;

    @OneToOne(mappedBy="pauta")
    private Sessao sessaodaPauta;

}
