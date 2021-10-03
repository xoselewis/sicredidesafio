package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "associado")
public class Associado extends Entidad {

    @Column(nullable = false)
    @NotBlank
    @CPF(message="cpf inválido")
    String cpf;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    String nome;

    @OneToMany(mappedBy = "associado", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos;

}
