package br.com.sicredidesafio.service;

import br.com.sicredidesafio.domain.Associado;
import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.Votacao;
import br.com.sicredidesafio.domain.repository.AssociadoRepository;
import br.com.sicredidesafio.domain.repository.SessaoRepository;
import br.com.sicredidesafio.domain.repository.VotacaoRepository;
import br.com.sicredidesafio.exceptiontreatment.NegocioException;
import br.com.sicredidesafio.exceptiontreatment.RegistroFaltanteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VotacaoService {

    @Autowired
    VotacaoRepository votacaoRepository;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    ObterStatusCPF ObterStatusCPF;

    public Votacao cadastrar(Votacao votacao) {
        try {

            if (!votacao.getVoto().trim().equalsIgnoreCase("Sim") && !votacao.getVoto().trim().equalsIgnoreCase("Não"))
                throw new NegocioException("O voto só pode ser Sim ou Não!");

            if (!ObterStatusCPF.statusAssociado(associadoRepository.findById(votacao.getAssociado().getId()).get().getCpf()))
                throw new NegocioException("Associado não pode participar da votação");

            Sessao sessao = sessaoRepository.findById(votacao.getSessao().getId()).orElseThrow(() -> new RegistroFaltanteException("Sessao não existe cadastrada para realizar votação!"));
            Associado associado = associadoRepository.findById(votacao.getAssociado().getId()).orElseThrow(() -> new RegistroFaltanteException("Associado não existe cadastrado para realizar votação!"));

            if(!sessao.isStatus())
                throw new NegocioException("Sessão de votação concluida para a Pauta");

            if(votacaoRepository.findBySessaoAndAssociado(sessao, associado) != null) {
                throw new NegocioException("Associado já realizou votação nesta Pauta");
            }

            votacao.setSessao(sessao);
            votacao.setAssociado(associado);
            return votacaoRepository.saveAndFlush(votacao);

        } catch (RegistroFaltanteException ex) {
            log.info(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.info("Erro ao salvar o voto -> " + ex.getMessage());
            throw new NegocioException("Erro ao salvar o voto -> " + ex.getMessage());
        }
    }

}
