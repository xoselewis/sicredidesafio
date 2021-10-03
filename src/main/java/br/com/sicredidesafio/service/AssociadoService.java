package br.com.sicredidesafio.service;

import br.com.sicredidesafio.domain.Associado;
import br.com.sicredidesafio.domain.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado cadastrar(Associado associado) {
        return associadoRepository.save(associado);
    }

    public void eliminar(Long IdAssociado) {
        associadoRepository.deleteById(IdAssociado);
    }
}
