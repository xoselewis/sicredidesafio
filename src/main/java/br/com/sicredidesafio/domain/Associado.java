package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "associado")
public class Associado extends Entidad {

    String cpf;

    @OneToMany(mappedBy = "associado", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos;

}
