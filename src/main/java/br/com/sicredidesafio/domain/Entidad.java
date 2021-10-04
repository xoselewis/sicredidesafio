package br.com.sicredidesafio.domain;

import br.com.sicredidesafio.domain.validacao.ValidacaoGrups;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Setter
@Getter
public class Entidad {

    @NotNull(groups = ValidacaoGrups.ValidaIdGrups.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //usado para marcar registro como eliminado na base de dados
    boolean deletedLogico = false;

}
