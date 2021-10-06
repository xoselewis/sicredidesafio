package br.com.sicredidesafio.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "associado", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos = new HashSet<>();

}
