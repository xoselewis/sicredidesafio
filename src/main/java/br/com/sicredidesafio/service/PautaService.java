package br.com.sicredidesafio.service;

import br.com.sicredidesafio.domain.Pauta;
import br.com.sicredidesafio.domain.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta cadastrar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public void eliminar(Long IdPauta) {
        pautaRepository.deleteById(IdPauta);
    }

}
