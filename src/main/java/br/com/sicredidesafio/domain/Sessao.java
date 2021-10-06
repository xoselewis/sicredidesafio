package br.com.sicredidesafio.domain;

import br.com.sicredidesafio.domain.validacao.ValidacaoGrups;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "sessao")
public class Sessao extends  Entidad {

    @Digits(message = "Número inválido",integer = 1, fraction = 0)
    @Min(1)
    @NotNull
    int tempoDuracao = 1;

    // Status true se sessão aberta
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    boolean status = true;

    @Valid
    @ConvertGroup(to = ValidacaoGrups.ValidaIdGrups.class)
    @NotNull
    @OneToOne
    @JoinColumn(name="pauta_id")
    private Pauta pauta;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "sessao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos = new HashSet<>();

    /* Pode ser usado se alterar logica na criação e inicio da sessão
     * @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     * LocalDate dataCriacaoSessao = LocalDate.now();
     *
     * @JsonProperty(access = JsonProperty.Access.READ_ONLY)
     * LocalDate datainicioSessao = LocalDate.now();
     * */

}
