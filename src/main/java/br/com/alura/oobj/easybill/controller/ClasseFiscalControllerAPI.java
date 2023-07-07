package br.com.alura.oobj.easybill.controller;

import br.com.alura.oobj.easybill.dto.DadosClasseFiscal;
import br.com.alura.oobj.easybill.dto.DadosNovaClasseFiscal;
import br.com.alura.oobj.easybill.model.ClasseFiscal;
import br.com.alura.oobj.easybill.repository.ClasseFiscalRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/classes-fiscais")
public class ClasseFiscalControllerAPI {

    @Autowired
    private ClasseFiscalRepository repository;

    @GetMapping
    public List<DadosClasseFiscal> listar(){
        return repository.findAll().stream().map(DadosClasseFiscal::new).toList();
    }

    @GetMapping("{id}")
    public Optional<DadosClasseFiscal> listarPorId(@PathVariable long id){
        return repository.findById(id).map(DadosClasseFiscal::new);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosNovaClasseFiscal> cadastrar(@RequestBody @Valid DadosNovaClasseFiscal novaClasseFiscal, UriComponentsBuilder uriBuilder){
        ClasseFiscal classeFiscal = novaClasseFiscal.toClasseFiscal();
        repository.save(classeFiscal);

        URI uri = uriBuilder.path("api/produtos/{id}").buildAndExpand(classeFiscal.getId()).toUri();
        return ResponseEntity.created(uri).body(novaClasseFiscal);
    }

    @PutMapping("{id}")
    @Transactional
    public void atualizar(@RequestBody @Valid DadosNovaClasseFiscal dadosClasseFiscalModificada, @PathVariable long id){
        var classeFiscal = repository.getReferenceById(id);
        ClasseFiscal classeFiscalModificada = dadosClasseFiscalModificada.toClasseFiscal();
        classeFiscal.atualizarInformacoes(classeFiscalModificada);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void deletar(@PathVariable long id){
        repository.deleteById(id);
    }

}
