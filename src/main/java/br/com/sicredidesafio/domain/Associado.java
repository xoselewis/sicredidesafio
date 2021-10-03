package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "associado")
public class Associado extends Entidad {

    @CPF(message="cpf inválido")
    String cpf;

    @OneToMany(mappedBy = "associado", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos;

}
