package br.com.sicredidesafio.service;

import br.com.sicredidesafio.domain.Pauta;
import br.com.sicredidesafio.domain.Sessao;
import br.com.sicredidesafio.domain.repository.PautaRepository;
import br.com.sicredidesafio.domain.repository.SessaoRepository;
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
    public void fechaSessaoVotacao(Sessao sessaoSemazenada) {

        try {
            long millis = sessaoSemazenada.getTempoDuracao() * 60000;
            Thread.sleep(millis);
            sessaoSemazenada.setStatus(false);
            sessaoRepository.saveAndFlush(sessaoSemazenada);
            log.info("Sessão de votação fechada \n");
        } catch (Exception ex) {
            log.info("Erro ao fechar sessão de votação \n" + ex.getMessage());
            throw new NegocioException("Erro ao fechar sessão de votação");
        }

    }


    /*Não implementada no controlador evitando Over Engineering
     * Soluçaõ semelhante a como implementada en outras Service
     * */
    public void eliminar(Long IdSessao) {
        sessaoRepository.deleteById(IdSessao);
    }

}
