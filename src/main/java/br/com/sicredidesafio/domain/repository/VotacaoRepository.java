package br.com.sicredidesafio.domain.repository;

import br.com.sicredidesafio.domain.Associado;
import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    Votacao findBySessaoAndAssociado(Sessao sessao, Associado associado);

}
