package br.com.sicredidesafio.domain.repository;

import br.com.sicredidesafio.domain.Pauta;
import br.com.sicredidesafio.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

     Sessao findByPauta(Pauta pauta);

}
