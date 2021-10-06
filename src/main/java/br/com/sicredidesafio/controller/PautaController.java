package br.com.sicredidesafio.controller;

import br.com.sicredidesafio.domain.Pauta;
import br.com.sicredidesafio.domain.repository.PautaRepository;
import br.com.sicredidesafio.service.PautaService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    PautaService pautaService;

    //***Requerimento solicitado no desafio***
    @PostMapping("/v1/cadastrarpauta")
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta cadastrar(@Valid @RequestBody Pauta pauta) {
        return pautaService.cadastrar(pauta);
    }

    /*Requerimento Adicionais colocado no desafio,
     * para consideração das habilidade, favor  não considerar como Over Engineering
     * comentando que não foi aplicado classe DTO (modelmapper), nem projection usando criteria
     * para retorno a mes classe do  Domain model e Representation usado  */

    @GetMapping("/v1/listarpauta")
    @Hidden
    public List<Pauta> listar() {
        return pautaRepository.findAll();
    }

    @GetMapping("/v1/obterpauta/{IdPauta}")
    @Hidden
    public ResponseEntity<Pauta> obter(@PathVariable Long IdPauta) {
        Optional<Pauta> optionalPauta = pautaRepository.findById(IdPauta);

        if(optionalPauta.isPresent()) {
            return ResponseEntity.ok(optionalPauta.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/v1/atualizarpauta/{IdPauta}")
    @Hidden
    public ResponseEntity<Pauta> atualizar(@Valid @PathVariable Long IdPauta, @RequestBody Pauta pauta) {

        if(!pautaRepository.existsById(IdPauta)) {
            return  ResponseEntity.notFound().build();
        }

        pauta.setId(IdPauta);
        pauta = pautaService.cadastrar(pauta);
        return  ResponseEntity.ok(pauta);
    }

    //elimina de forma fisica
    @DeleteMapping("/v1/eliminarpauta/{IdPauta}")
    @Hidden
    public ResponseEntity<Void> eliminar(@PathVariable Long IdPauta) {
        if(!pautaRepository.existsById(IdPauta)) {
            return  ResponseEntity.notFound().build();
        }

        pautaService.eliminar(IdPauta);
        return  ResponseEntity.noContent().build();
    }

}
