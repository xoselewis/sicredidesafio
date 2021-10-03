package br.com.sicredidesafio.controller;

import br.com.sicredidesafio.domain.Associado;
import br.com.sicredidesafio.domain.repository.AssociadoRepository;
import br.com.sicredidesafio.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    AssociadoService associadoService;

    //Requerimento solicitado no desafio
    @PostMapping("/v1/cadastrarassociado")
    @ResponseStatus(HttpStatus.CREATED)
    public Associado cadastrar(@Valid @RequestBody Associado associado) {
        return associadoService.cadastrar(associado);
    }

    // Requerimento Adicionais
    @GetMapping("/v1/listarassociado")
    public List<Associado> listar() {
        return associadoRepository.findAll();
    }

    @GetMapping("/v1/obterassociado/{IdAssociado}")
    public ResponseEntity<Associado> obter(@PathVariable Long IdAssociado) {
        Optional<Associado> optionalAssociado = associadoRepository.findById(IdAssociado);

        if(optionalAssociado.isPresent()) {
            return ResponseEntity.ok(optionalAssociado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/atualizarassociado/{IdAssociado}")
    public ResponseEntity<Associado> atualizar(@Valid @PathVariable Long IdAssociado, @RequestBody Associado associado) {

        if(!associadoRepository.existsById(IdAssociado)) {
            return  ResponseEntity.notFound().build();
        }

        associado.setId(IdAssociado);
        associado = associadoService.cadastrar(associado);
        return  ResponseEntity.ok(associado);
    }

    //elimina de forma fisica
    @DeleteMapping("/v1/eliminarassociado/{IdAssociado}")
    public ResponseEntity<Void> eliminar(@PathVariable Long IdAssociado) {
        if(!associadoRepository.existsById(IdAssociado)) {
            return  ResponseEntity.notFound().build();
        }

        associadoService.eliminar(IdAssociado);
        return  ResponseEntity.noContent().build();
    }

}
