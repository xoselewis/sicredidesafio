package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@MappedSuperclass
@Setter
@Getter
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //usado para marcar registro como eliminado na base de dados
    boolean deletedLogico = false;

}
