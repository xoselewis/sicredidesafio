package br.com.sicredidesafio.domain.repository;

import br.com.sicredidesafio.domain.Associado;
import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    Votacao findBySessaoAndAssociado(Sessao sessao, Associado associado);

    long countBySessao(Sessao sessao);

    @Query(value = "Select count(v.voto) from Votacao v where v.voto like \'sim\' and v.sessao.id=:sessaoId")
    long countBySessaoVotosSim(long sessaoId);

}
