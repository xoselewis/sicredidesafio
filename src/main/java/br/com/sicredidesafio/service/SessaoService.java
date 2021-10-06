package br.com.sicredidesafio.service;

import br.com.sicredidesafio.MensageriaFila.ResultadoVotacaoSender;
import br.com.sicredidesafio.domain.Pauta;
import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.repository.PautaRepository;
import br.com.sicredidesafio.domain.repository.SessaoRepository;
import br.com.sicredidesafio.domain.repository.VotacaoRepository;
import br.com.sicredidesafio.exceptiontreatment.NegocioException;
import br.com.sicredidesafio.exceptiontreatment.RegistroFaltanteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SessaoService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    SessaoRepository sessaoRepository;


    @Autowired
    private ResultadoVotacaoSender resultadoVotacaoSender;

    @Autowired
    VotacaoRepository votacaoRepository;

    public Sessao cadastrar(Sessao sessao) {
        try {
            Pauta pauta = pautaRepository.findById(sessao.getPauta().getId()).orElseThrow(() -> new RegistroFaltanteException("Pauta não existe cadastrada para criar uma sessão!"));

            if(sessaoRepository.findByPauta(pauta) != null)
                throw new NegocioException("Pauta já vinculada em Sessão");

            sessao.setPauta(pauta);
            sessao.setStatus(true);
            return sessaoRepository.saveAndFlush(sessao);
        } catch (RegistroFaltanteException ex) {
            log.info(ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.info("Erro ao salvar Sessão \n" + ex.getMessage());
            throw new NegocioException("Erro ao salvar Sessão -> " + ex.getMessage());
        }
    }

    @Async
    public void fechaSessaoVotacao(Sessao sessaoArmazenada) {

        try {
            long millis = sessaoArmazenada.getTempoDuracao() * 60000;
            Thread.sleep(millis);
            sessaoArmazenada.setStatus(false);
            sessaoRepository.saveAndFlush(sessaoArmazenada);
            log.info("Sessão de votação fechada \n");
        } catch (Exception ex) {
            log.info("Erro ao fechar sessão de votação \n" + ex.getMessage());
            throw new NegocioException("Erro ao fechar sessão de votação");
        }

        devolverResuladatoVotacao(sessaoArmazenada);

    }

    private void devolverResuladatoVotacao(Sessao sessaoFechada) {

        long quantidadeVotosSessaoTotais  =  votacaoRepository.countBySessao(sessaoFechada);
        long quantidadeVotosSessaoSim  =  votacaoRepository.countBySessaoVotosSim(sessaoFechada.getId());
        long quantidadeVotosSessaoNao  =  quantidadeVotosSessaoTotais - quantidadeVotosSessaoSim;

        Long IdentificacaoPauta = sessaoFechada.getPauta().getId();
        String descricaoPauta = sessaoFechada.getPauta().getDescricao();

        log.info("Descricao de Pauta: " + IdentificacaoPauta + " -> " + descricaoPauta + "\n");
        log.info("Número de Votos Totais: " + quantidadeVotosSessaoTotais + " Número de Votos Sim: " + quantidadeVotosSessaoSim + " Número de Votos Não: " + quantidadeVotosSessaoNao);


        String mensagem = "{numeroVotosTotais:" + quantidadeVotosSessaoTotais  + ","
                +     " mumeroVotosSim: " + quantidadeVotosSessaoSim +    ","
                + " numeroVotosNao: " + quantidadeVotosSessaoNao + "}";

        envioMensagemLista(mensagem);

    }

    public void envioMensagemLista(String mensagemresultado) {
        try {
            resultadoVotacaoSender.send(mensagemresultado);
            log.info("Mensagem enviada!");
        } catch (Exception exception) {
            log.info("Erro ao fechar sessão de votação \n" + exception.getMessage());
            throw new NegocioException("Erro no envio de mensagem");
        }

    }

    /*Não implementada no controlador evitando Over Engineering
     * Soluçaõ semelhante a como implementada en outras Service
     * */
    public void eliminar(Long IdSessao) {
        sessaoRepository.deleteById(IdSessao);
    }

}
