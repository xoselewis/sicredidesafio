package br.com.sicredidesafio.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "sessao")
public class Sessao extends  Entidad {

    int tempoDuracao = 1;

    // Status true se sessão aberta
    boolean status;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="pauta_id")
    private Pauta pauta;

    @OneToMany(mappedBy = "sessao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Votacao> votos;

}
