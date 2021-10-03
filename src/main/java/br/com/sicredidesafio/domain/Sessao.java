package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "sessao")
public class Sessao extends  Entidad {
    
    
    @Digits(message = "Número inválido",integer = 0, fraction = 0)
    int tempoDuracao = 1;

    // Status true se sessão aberta
    @NotNull
    boolean status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="pauta_id")
    private Pauta pauta;

    @OneToMany(mappedBy = "sessao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos;

}
